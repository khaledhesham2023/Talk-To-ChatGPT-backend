package com.chatai.demo.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "openai")
public class OpenAIConfig {

    private String apiKey;
    private String url;
    private String chatCompletionModel;
    private String transcriptionModel;
    private String textSpeechModel;
    private String pathname;

    public OpenAIConfig(String apiKey, String url, String chatCompletionModel, String transcriptionModel, String textSpeechModel, String pathname) {
        this.apiKey = apiKey;
        this.url = url;
        this.chatCompletionModel = chatCompletionModel;
        this.transcriptionModel = transcriptionModel;
        this.textSpeechModel = textSpeechModel;
        this.pathname = pathname;
    }

    public OpenAIConfig() {
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setChatCompletionModel(String chatCompletionModel) {
        this.chatCompletionModel = chatCompletionModel;
    }

    public String getChatCompletionModel() {
        return chatCompletionModel;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public String getPathname() {
        return pathname;
    }

    public void setTextSpeechModel(String textSpeechModel) {
        this.textSpeechModel = textSpeechModel;
    }

    public String getTextSpeechModel() {
        return textSpeechModel;
    }

    public void setTranscriptionModel(String transcriptionModel) {
        this.transcriptionModel = transcriptionModel;
    }

    public String getTranscriptionModel() {
        return transcriptionModel;
    }
}