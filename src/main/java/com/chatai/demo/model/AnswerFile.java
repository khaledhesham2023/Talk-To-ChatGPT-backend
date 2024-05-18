package com.chatai.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class AnswerFile {
    private MultipartFile answerFile;
    private String answerFileName;

    public AnswerFile(MultipartFile answerFile, String answerFileName) {
        this.answerFile = answerFile;
        this.answerFileName = answerFileName;
    }

    public AnswerFile() {
    }

    public MultipartFile getAnswerFile() {
        return answerFile;
    }

    public String getAnswerFileName() {
        return answerFileName;
    }
}
