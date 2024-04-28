package com.chatai.demo.model.response;

public class SpeechResponse {
    private String text;

    public SpeechResponse(String text) {
        this.text = text;
    }

    public SpeechResponse() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}