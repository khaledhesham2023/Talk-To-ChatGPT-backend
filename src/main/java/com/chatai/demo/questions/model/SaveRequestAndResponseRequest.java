package com.chatai.demo.questions.model;


public class SaveRequestAndResponseRequest {

    private String request;
    private String response;

    public SaveRequestAndResponseRequest(String request, String response) {
        this.request = request;
        this.response = response;
    }

    public SaveRequestAndResponseRequest() {
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
