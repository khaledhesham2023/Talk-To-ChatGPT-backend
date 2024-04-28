package com.chatai.demo.model.request;

public class TextToSpeechRequest {
    private String model;
    private String input;
    private String voice;

    public TextToSpeechRequest(String model, String input, String voice) {
        this.model = model;
        this.input = input;
        this.voice = voice;
    }

    public TextToSpeechRequest() {
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getModel() {
        return model;
    }

    public String getInput() {
        return input;
    }

    public String getVoice() {
        return voice;
    }
}
