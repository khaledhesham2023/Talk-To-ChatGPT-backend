package com.chatai.demo.questions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class QuestionRequest {
    private String question;
    private String answer;
    private String audioQuestionFileName;
    private String createdTime;


    public QuestionRequest(String question, String answer, String audioQuestionFileName, String createdTime) {
        this.question = question;
        this.answer = answer;
        this.audioQuestionFileName = audioQuestionFileName;
        this.createdTime = createdTime;
    }

    public QuestionRequest() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAudioFileName(String audioQuestionFileName) {
        this.audioQuestionFileName = audioQuestionFileName;
    }

    public String getAudioQuestionFileName() {
        return audioQuestionFileName;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}