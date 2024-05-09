package com.chatai.demo.model;

public class CompletionObject {
    private String request;
    private String response;

    public CompletionObject(String request, String response) {
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
}
