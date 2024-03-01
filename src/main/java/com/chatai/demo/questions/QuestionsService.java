package com.chatai.demo.questions;

import com.chatai.demo.questions.model.BaseResponse;
import com.chatai.demo.questions.model.QuestionRequest;

import java.util.List;

public interface QuestionsService {
    BaseResponse saveQuestion(QuestionRequest request);
    List<QuestionsEntity> getAllQuestion();
}
