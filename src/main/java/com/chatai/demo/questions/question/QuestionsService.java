package com.chatai.demo.questions.question;

import com.chatai.demo.model.Answer;
import com.chatai.demo.model.AnswerData;
import com.chatai.demo.model.AnswerFile;
import com.chatai.demo.model.response.ChatCompletionResponse;
import com.chatai.demo.model.response.SpeechResponse;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface QuestionsService {
    List<QuestionsEntity> getAllQuestion();

    byte[] getAnswer(MultipartFile questionFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    long convertSpeechToText(MultipartFile questionFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    long questionToAnswer(long sttId) throws IOException;

    Answer convertTextToSpeech(long qtaId) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
