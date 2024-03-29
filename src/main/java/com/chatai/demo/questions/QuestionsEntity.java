package com.chatai.demo.questions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questions",schema = "chataiapp")
public class QuestionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "question", columnDefinition = "LONGTEXT")
    private String question;

    @Column(name = "answer", columnDefinition = "LONGTEXT")
    private String answer;

    @Column(name = "voice_question_file_name", columnDefinition = "VARCHAR(255)")
    private String audioQuestionFileName;

    @Column(name = "voice_answer_file_name", columnDefinition = "VARCHAR(255)")
    private String audioAnswerFileName;

    @Column(name = "request",columnDefinition = "JSON")
    private String request;

    @Column(name = "response",columnDefinition = "JSON")
    private String response;

    @Column(name = "created_time",columnDefinition = "VARCHAR(255)")
    private String createdTime;


    public QuestionsEntity() {
    }

    public QuestionsEntity(Long id, String question, String answer, String audioQuestionFileName, String createdTime) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.audioQuestionFileName = audioQuestionFileName;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAudioQuestionFileName(String audioQuestionFileName) {
        this.audioQuestionFileName = audioQuestionFileName;
    }

    public String getAudioQuestionFileName() {
        return audioQuestionFileName;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setAudioAnswerFileName(String audioAnswerFileName) {
        this.audioAnswerFileName = audioAnswerFileName;
    }

    public String getAudioAnswerFileName() {
        return audioAnswerFileName;
    }
}
