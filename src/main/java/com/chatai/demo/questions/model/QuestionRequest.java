package com.chatai.demo.questions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class QuestionRequest {
    private String question;
    private String answer;
    private String audioFileName;

    public QuestionRequest(String question, String answer, String audioFileName) {
        this.question = question;
        this.answer = answer;
        this.audioFileName = audioFileName;
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

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }

    public String getAudioFileName() {
        return audioFileName;
    }
}
