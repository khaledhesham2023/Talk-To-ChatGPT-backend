package com.chatai.demo.questions;

import com.chatai.demo.questions.model.BaseResponse;
import com.chatai.demo.questions.model.QuestionRequest;
import com.chatai.demo.questions.model.SaveRequestAndResponseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("V1/rest/")
public class QuestionController {

    @Autowired
    private QuestionsService questionsService;

    @PostMapping("questions/save")
    public ResponseEntity<BaseResponse> saveQuestion(@RequestBody QuestionRequest request) {
        return ResponseEntity.ok(questionsService.saveQuestion(request));
    }

    @GetMapping("questions")
    public ResponseEntity<List<QuestionsEntity>> getAllQuestions(){
        return ResponseEntity.ok(questionsService.getAllQuestion());
    }

    @PostMapping("saveRequestAndResponse/{audioName}")
    public ResponseEntity<BaseResponse> saveRequestAndResponse(@PathVariable("audioName") String audioName, @RequestBody SaveRequestAndResponseRequest request){
        return ResponseEntity.ok(questionsService.saveRequestAndResponse(audioName, request));
    }
}
