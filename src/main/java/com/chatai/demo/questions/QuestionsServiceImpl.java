package com.chatai.demo.questions;

import com.chatai.demo.questions.model.BaseResponse;
import com.chatai.demo.questions.model.QuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsRepo questionsRepo;

    @Override
    public BaseResponse saveQuestion(QuestionRequest request) {
        QuestionsEntity question = new QuestionsEntity(null, request.getQuestion(), request.getAnswer(), request.getAudioFileName());
        questionsRepo.save(question);
        return new BaseResponse(true, "Question saved successfully");
    }

    @Override
    public List<QuestionsEntity> getAllQuestion() {
        return questionsRepo.findAll();
    }
}
