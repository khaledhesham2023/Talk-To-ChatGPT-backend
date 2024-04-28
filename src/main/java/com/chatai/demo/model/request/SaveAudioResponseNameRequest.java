package com.chatai.demo.model.request;

public class SaveAudioResponseNameRequest {
    private String audioQuestionFileName;
    private String audioAnswerFileName;

    public SaveAudioResponseNameRequest(String audioQuestionFileName, String audioAnswerFileName) {
        this.audioQuestionFileName = audioQuestionFileName;
        this.audioAnswerFileName = audioAnswerFileName;
    }

    public SaveAudioResponseNameRequest() {
    }

    public void setAudioAnswerFileName(String audioAnswerFileName) {
        this.audioAnswerFileName = audioAnswerFileName;
    }

    public String getAudioAnswerFileName() {
        return audioAnswerFileName;
    }

    public void setAudioQuestionFileName(String audioQuestionFileName) {
        this.audioQuestionFileName = audioQuestionFileName;
    }

    public String getAudioQuestionFileName() {
        return audioQuestionFileName;
    }
}
