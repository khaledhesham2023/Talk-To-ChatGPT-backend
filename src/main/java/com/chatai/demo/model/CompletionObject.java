package com.chatai.demo.model;

public class CompletionObject {
    private String answerText;
    private String request;
    private String response;

    public CompletionObject(String answerText, String request, String response) {
        this.answerText = answerText;
        this.request = request;
        this.response = response;
    }

    public CompletionObject() {
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }
}
