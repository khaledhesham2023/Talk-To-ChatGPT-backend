package com.chatai.demo.questions.question;

import com.chatai.demo.model.AnswerData;
import com.chatai.demo.model.AnswerFile;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface QuestionsService {
    List<QuestionsEntity> getAllQuestion();
    QuestionsEntity insertQuestion(String answer,
                                   MultipartFile answerFile,
                                   MultipartFile questionFile,
                                   String question,
                                   String request,
                                   String response
    ) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    byte[] getAnswer(MultipartFile questionFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
