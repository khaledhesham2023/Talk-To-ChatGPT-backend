package com.chatai.demo.questions.question;

import com.chatai.demo.utils.OpenAIConfig;
import io.minio.errors.*;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("V1/rest/")
public class QuestionController {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private OpenAIConfig openAIConfig;

    @GetMapping("questions")
    public ResponseEntity<List<QuestionsEntity>> getAllQuestions() {
        return ResponseEntity.ok(questionsService.getAllQuestion());
    }

    @PostMapping("insert")
    public ResponseEntity<QuestionsEntity> insertQuestion(
            @RequestParam("answer") String answer,
            @RequestParam("answer_file") MultipartFile answerFile,
            @RequestParam("question_file") MultipartFile questionFile,
            @RequestParam("question") String question,
            @RequestParam("request") String request,
            @RequestParam("response") String response
    ) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(questionsService.insertQuestion(answer, answerFile, questionFile, question, request, response));
    }

    @PostMapping("answer")
    public ResponseEntity<byte[]> getSuccess(@RequestParam("question_file") MultipartFile questionFile) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(questionsService.getAnswer(questionFile));
    }
}