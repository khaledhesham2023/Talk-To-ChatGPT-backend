package com.chatai.demo.questions.question;

import com.chatai.demo.model.Answer;
import com.chatai.demo.model.AnswerFile;
import com.chatai.demo.model.response.ChatCompletionResponse;
import com.chatai.demo.model.response.SpeechResponse;
import com.chatai.demo.questiontoanswer.QuestionToAnswerEntity;
import com.chatai.demo.questiontoanswer.QuestionToAnswerRepo;
import com.chatai.demo.speechtotext.SpeechToTextEntity;
import com.chatai.demo.speechtotext.SpeechToTextRepo;
import com.chatai.demo.texttospeech.TextToSpeechEntity;
import com.chatai.demo.texttospeech.TextToSpeechRepo;
import com.chatai.demo.utils.ChatOpenAIClient;
import com.chatai.demo.utils.MinioUploader;
import com.chatai.demo.utils.OpenAIConfig;
import com.google.gson.Gson;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsRepo questionsRepo;

    @Autowired
    private SpeechToTextRepo speechToTextRepo;

    @Autowired
    private QuestionToAnswerRepo questionToAnswerRepo;

    @Autowired
    private TextToSpeechRepo textToSpeechRepo;

    @Autowired
    private MinioUploader minioUploader;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OpenAIConfig openAIConfig;

    @Autowired
    private ChatOpenAIClient chatOpenAIClient;


    @Override
    public List<QuestionsEntity> getAllQuestion() {
        return questionsRepo.findAll();
    }


    @Override
    public byte[] getAnswer(MultipartFile questionFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        long sttRequestId = convertSpeechToText(questionFile);
        long qtaRequestId = questionToAnswer(sttRequestId);
        Answer answer = convertTextToSpeech(qtaRequestId);
        QuestionsEntity questionsEntity = new QuestionsEntity(null,speechToTextRepo.findById(sttRequestId).orElseThrow(),questionToAnswerRepo.findById(qtaRequestId).orElseThrow(),textToSpeechRepo.findById(answer.getRequestId()).orElseThrow(),getCurrentTime(System.currentTimeMillis()));
        questionsRepo.save(questionsEntity);
        return answer.getFileBytes();
    }

    /**
     * Sends an API request to OpenAI for transcription of client's voice file into a text.
     * Stores the file in a local storage to be uploaded to object storage bucket (MinIO).
     * Creates a record that will be stored into MySQL Database and Kafka Topic.
     * @param questionFile the voice file sent from the client.
     * @return the request ID of the record.
     */
    @Override
    public long convertSpeechToText(MultipartFile questionFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String questionFileName = getQuestionFileName();
        // Create a regular file for uploading into MinIO Bucket.
        minioUploader.uploadFileIntoMinio(questionFile,questionFileName);
        // An API call to OpenAI to convert mp3 file into a question text.
        SpeechResponse speechResponse = chatOpenAIClient.getSpeechText(questionFile);
        // Creating a record for Speech-To-Text process to be logged into MySQL Database and Kafka Topic.
        SpeechToTextEntity speechToTextEntity = new SpeechToTextEntity(null, speechResponse.getText(), questionFileName, questionFile.getOriginalFilename(), new Gson().toJson(speechResponse));
        speechToTextRepo.save(speechToTextEntity);
        kafkaTemplate.send(openAIConfig.getTopicName(), speechToTextEntity.toString());
        return speechToTextEntity.getSttId();
    }

    /**
     * Sends an API request to OpenAI for completion purposes by giving the proper answer to the given question.
     * Creates a record for the completion process to be stored into MYSQL Database and Kafka Topic.
     * @param sttId The request ID of the transcription process
     * @return The request ID of the chat completion process.
     */
    @Override
    public long questionToAnswer(long sttId) throws IOException {
        // Getting the transcription record for chat completion of the saved question text.
        SpeechToTextEntity speechToTextEntity = speechToTextRepo.findById(sttId).orElseThrow();
        // Sending API request to OpenAI get the appropriate answer text.
        ChatCompletionResponse chatCompletionResponse = chatOpenAIClient.getAnswerText(speechToTextEntity.getQuestion());
        // Creating a record for chat completion process and logging it into MySQL Database and Kafka Topic.
        QuestionToAnswerEntity questionToAnswerEntity = new QuestionToAnswerEntity(
                null,
                chatCompletionResponse.getChoices().get(0).getMessage().getContent(),
                new Gson().toJson(new SpeechResponse(speechToTextEntity.getQuestion())),
                new Gson().toJson(chatCompletionResponse),
                speechToTextEntity
        );
        questionToAnswerRepo.save(questionToAnswerEntity);
        kafkaTemplate.send(openAIConfig.getTopicName(), questionToAnswerEntity.toString());
        return questionToAnswerEntity.getQtaId();
    }

    /**
     * Converts a given text into an AI-voiced file in mp3 format by sending an API request to OpenAI.
     * Uploads the created file into Object Storage Bucket(MinIO)
     * Logs the record into MySQL Database and Kafka Topic.
     * @param qtaId the ID for chat completion record.
     * @return An object containing two properties: ID of Text-To-Speech record and voice file as a byteArray.
     */
    @Override
    public Answer convertTextToSpeech(long qtaId) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
       // Get the last chat completion record by record's ID
        QuestionToAnswerEntity questionToAnswerEntity = questionToAnswerRepo.findById(qtaId).orElseThrow();
        // Sending an API request to convert the answer text into an AI-voiced record file of the answer.
        AnswerFile answerFile = chatOpenAIClient.getAnswerVoiceFile(questionToAnswerEntity.getAnswer());
        // uploading answer file into object storage bucket (MinIO).
        minioUploader.uploadFileIntoMinio(answerFile.getAnswerFile(),answerFile.getAnswerFileName());
        // converting the voice file into a byteArray to be sent back to the client.
        File tempFile = File.createTempFile("temp", null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(answerFile.getAnswerFile().getBytes());
        }
        Path path = tempFile.toPath();
        byte[] answerFileBytes = Files.readAllBytes(path);
        // creating a record of text-to-speech and logging it into MySQL server and kafka topic
        TextToSpeechEntity textToSpeechEntity = new TextToSpeechEntity(null, answerFile.getAnswerFileName(), new Gson().toJson(new SpeechResponse(questionToAnswerEntity.getAnswer())), answerFile.getAnswerFile().getOriginalFilename(),questionToAnswerEntity);
        textToSpeechRepo.save(textToSpeechEntity);
        kafkaTemplate.send(openAIConfig.getTopicName(), textToSpeechEntity.toString());
        tempFile.delete();
        return new Answer(answerFileBytes, textToSpeechEntity.getTtsId());
    }

    @Override
    public byte[] getAnswerFromText(SpeechResponse speechResponse) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
       SpeechToTextEntity speechToTextEntity = new SpeechToTextEntity(null,speechResponse.getText(),null,null,null);
        // Sending API request to OpenAI get the appropriate answer text.
        ChatCompletionResponse chatCompletionResponse = chatOpenAIClient.getAnswerText(speechResponse.getText());
        // Creating a record for chat completion process and logging it into MySQL Database and Kafka Topic.
        QuestionToAnswerEntity questionToAnswerEntity = new QuestionToAnswerEntity(
                null,
                chatCompletionResponse.getChoices().get(0).getMessage().getContent(),
                new Gson().toJson(speechResponse),
                new Gson().toJson(chatCompletionResponse),
                speechToTextEntity
        );
        questionToAnswerRepo.save(questionToAnswerEntity);
        kafkaTemplate.send(openAIConfig.getTopicName(), questionToAnswerEntity.toString());
        Answer answer = convertTextToSpeech(questionToAnswerEntity.getQtaId());
        QuestionsEntity questionsEntity = new QuestionsEntity(null,speechToTextEntity,questionToAnswerRepo.findById(questionToAnswerEntity.getQtaId()).orElseThrow(),textToSpeechRepo.findById(answer.getRequestId()).orElseThrow(),getCurrentTime(System.currentTimeMillis()));
        questionsRepo.save(questionsEntity);
        return answer.getFileBytes();
    }


    private String getCurrentTime(long timeInMilliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(new Date(timeInMilliSeconds));
    }
    private String getQuestionFileName() {
        return "question-" + System.currentTimeMillis() + new Random().nextInt(1000000000) + ".mp3";
    }
}
