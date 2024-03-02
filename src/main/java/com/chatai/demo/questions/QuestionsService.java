package com.chatai.demo.questions;

import com.chatai.demo.questions.model.BaseResponse;
import com.chatai.demo.questions.model.QuestionRequest;
import com.chatai.demo.questions.model.SaveRequestAndResponseRequest;

import java.util.List;

public interface QuestionsService {
    BaseResponse saveQuestion(QuestionRequest request);
    List<QuestionsEntity> getAllQuestion();

    BaseResponse saveRequestAndResponse(String audioName, SaveRequestAndResponseRequest request);
}
