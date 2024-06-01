package com.chatai.demo.questions.question;

import com.chatai.demo.model.response.SpeechResponse;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("V1/rest/")
public class QuestionController {

    @Autowired
    private QuestionsService questionsService;


    @GetMapping("questions")
    public ResponseEntity<List<QuestionsEntity>> getAllQuestions() {
        return ResponseEntity.ok(questionsService.getAllQuestion());
    }

    @PostMapping("voice-to-voice")
    public ResponseEntity<byte[]> getAnswer(@RequestParam("question_file") MultipartFile questionFile) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(questionsService.getAnswer(questionFile));
    }
    @PostMapping("question-to-voice")
    public ResponseEntity<byte[]> getAnswerFromText(@RequestBody SpeechResponse speechResponse) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(questionsService.getAnswerFromText(speechResponse));
    }
}