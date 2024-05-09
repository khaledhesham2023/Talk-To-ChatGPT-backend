package com.chatai.demo.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
    private String topicName;

    public OpenAIConfig(String apiKey, String url, String chatCompletionModel, String transcriptionModel, String textSpeechModel, String pathname, String topicName) {
        this.apiKey = apiKey;
        this.url = url;
        this.chatCompletionModel = chatCompletionModel;
        this.transcriptionModel = transcriptionModel;
        this.textSpeechModel = textSpeechModel;
        this.pathname = pathname;
        this.topicName = topicName;
    }

    public OpenAIConfig() {
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setChatCompletionModel(String chatCompletionModel) {
        this.chatCompletionModel = chatCompletionModel;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public void setTextSpeechModel(String textSpeechModel) {
        this.textSpeechModel = textSpeechModel;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTranscriptionModel(String transcriptionModel) {
        this.transcriptionModel = transcriptionModel;
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

    public String getChatCompletionModel() {
        return chatCompletionModel;
    }

    public String getPathname() {
        return pathname;
    }

    public String getTextSpeechModel() {
        return textSpeechModel;
    }


    public String getTranscriptionModel() {
        return transcriptionModel;
    }

    public String getTopicName() {
        return topicName;
    }
}