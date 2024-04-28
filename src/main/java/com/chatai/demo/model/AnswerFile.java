package com.chatai.demo.model;

import java.io.File;

public class AnswerFile {
    private File answerFile;
    private String answerFileName;

    public AnswerFile(File answerFile, String answerFileName) {
        this.answerFile = answerFile;
        this.answerFileName = answerFileName;
    }

    public AnswerFile() {
    }

    public void setAnswerFile(File answerFile) {
        this.answerFile = answerFile;
    }

    public void setAnswerFileName(String answerFileName) {
        this.answerFileName = answerFileName;
    }

    public File getAnswerFile() {
        return answerFile;
    }

    public String getAnswerFileName() {
        return answerFileName;
    }
}
