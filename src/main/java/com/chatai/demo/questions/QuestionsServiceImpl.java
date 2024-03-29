package com.chatai.demo.questions;

import com.chatai.demo.questions.model.BaseResponse;
import com.chatai.demo.questions.model.QuestionRequest;
import com.chatai.demo.questions.model.SaveAudioResponseNameRequest;
import com.chatai.demo.questions.model.SaveRequestAndResponseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsRepo questionsRepo;

    @Override
    public BaseResponse saveQuestion(QuestionRequest request) {
        QuestionsEntity question = new QuestionsEntity(null, request.getQuestion(), request.getAnswer(), request.getAudioQuestionFileName(), request.getCreatedTime());
        questionsRepo.save(question);
        return new BaseResponse(true, "Question saved successfully");
    }

    @Override
    public List<QuestionsEntity> getAllQuestion() {
        return questionsRepo.findAll();
    }

    @Override
    public BaseResponse saveRequestAndResponse(String audioName, SaveRequestAndResponseRequest request) {
        QuestionsEntity questionsEntity = questionsRepo.findByAudioQuestionFileName(audioName);
        if (questionsEntity != null){
            questionsEntity.setRequest(request.getRequest());
            questionsEntity.setResponse(request.getResponse());
            questionsRepo.save(questionsEntity);
            return new BaseResponse(true, "Request and Response modified successfully");
        } else {
            return new BaseResponse(false, "Question not exists");
        }
    }

    @Override
    public BaseResponse saveAudioResponseName(SaveAudioResponseNameRequest request) {
        QuestionsEntity questionsEntity = questionsRepo.findByAudioQuestionFileName(request.getAudioQuestionFileName());
        if (questionsEntity != null){
            questionsEntity.setAudioAnswerFileName(request.getAudioAnswerFileName());
            questionsRepo.save(questionsEntity);
            return new BaseResponse(true,"Audio answer saved successfully");
        } else {
            return new BaseResponse(false,"Question not exist");
        }

    }
}
