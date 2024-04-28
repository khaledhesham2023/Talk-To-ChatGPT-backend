package com.chatai.demo.questions.question;

import com.chatai.demo.model.AnswerData;
import com.chatai.demo.model.AnswerFile;
import com.chatai.demo.model.CompletionObject;
import com.chatai.demo.model.response.SpeechResponse;
import com.chatai.demo.utils.ChatOpenAIClient;
import com.chatai.demo.utils.MinioUploader;
import com.chatai.demo.utils.OpenAIConfig;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsRepo questionsRepo;

    @Autowired
    private MinioUploader minioUploader;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OpenAIConfig openAIConfig;

    @Autowired
    private ChatOpenAIClient chatOpenAIClient;

    private final ResourceLoader resourceLoader;

    public QuestionsServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<QuestionsEntity> getAllQuestion() {
        return questionsRepo.findAll();
    }

    @Override
    public QuestionsEntity insertQuestion(String answer, MultipartFile answerFile, MultipartFile questionFile, String question, String request, String response) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        File voiceAnswer = uploadFileOnLocalStorage(answerFile);
        File voiceQuestion = uploadFileOnLocalStorage(questionFile);
        minioUploader.uploadFileIntoMinio(voiceAnswer);
        minioUploader.uploadFileIntoMinio(voiceQuestion);
        QuestionsEntity questionsEntity = new QuestionsEntity(null, question, answer, getCurrentTime(System.currentTimeMillis()), request, response, questionFile.getOriginalFilename(), answerFile.getOriginalFilename());
        questionsRepo.save(questionsEntity);
        kafkaTemplate.send("ChatOpenAITopic", questionsEntity.toString());
        return questionsEntity;
    }

    @Override
    public byte[] getAnswer(MultipartFile questionFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        File voiceQuestion = uploadFileOnLocalStorage(questionFile);
//        Resource resource = resourceLoader.getResource(voiceQuestion.getAbsolutePath());
        minioUploader.uploadFileIntoMinio(voiceQuestion);
        System.out.println("uploading question voice file to minio complete");
        SpeechResponse speechResponse = chatOpenAIClient.getSpeechText(voiceQuestion);
        System.out.println("speech to text complete");
        CompletionObject completionObject = chatOpenAIClient.getAnswerText(speechResponse.getText());
        System.out.println("completion complete");
        AnswerFile answerFile = chatOpenAIClient.getAnswerVoiceFile(completionObject.getAnswerText());
        System.out.println("text to speech completed");
        minioUploader.uploadFileIntoMinio(answerFile.getAnswerFile());
        System.out.println("uploading answer voice file to minio complete");
        QuestionsEntity questionsEntity = new QuestionsEntity(null, speechResponse.getText(), completionObject.getAnswerText(), getCurrentTime(System.currentTimeMillis()), completionObject.getRequest(), completionObject.getResponse(), voiceQuestion.getName(), answerFile.getAnswerFileName());
        questionsRepo.save(questionsEntity);
        System.out.println("saving data to database completed");
//        kafkaTemplate.send("ChatOpenAITopic", questionsEntity.toString());
//        System.out.println("saving data on kafka topic complete");
//        return answerFile.getAnswerFile();
        Path path = Paths.get(answerFile.getAnswerFile().getAbsolutePath());
        return Files.readAllBytes(path);
    }


    private String getCurrentTime(long timeInMilliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(new Date(timeInMilliSeconds));
    }


    private File uploadFileOnLocalStorage(MultipartFile file) throws IOException {
        String dir = openAIConfig.getPathname();
        String filePath = dir + file.getOriginalFilename();
        File uploadedFile = new File(filePath);
        file.transferTo(uploadedFile);
        return uploadedFile;
    }
}
