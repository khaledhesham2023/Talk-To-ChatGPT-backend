package com.chatai.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class QuestionRequest {
    @SerializedName("question")
    private String question;

    @SerializedName("answer")
    private String answer;
    @SerializedName("createdTime")
    private String createdTime;
    @SerializedName("request")
    private String request;
    @SerializedName("response")
    private String response;


    public QuestionRequest(String question, String answer, String createdTime, String request, String response) {
        this.question = question;
        this.answer = answer;
        this.createdTime = createdTime;
        this.request = request;
        this.response = response;
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

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public String getRequest() {
        return request;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}