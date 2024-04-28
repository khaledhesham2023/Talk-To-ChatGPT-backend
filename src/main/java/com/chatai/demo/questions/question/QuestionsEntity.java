package com.chatai.demo.questions.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String request;

    @Column(name = "response",columnDefinition = "JSON")
    @JsonIgnore
    private String response;

    @Column(name = "created_time",columnDefinition = "VARCHAR(255)")
    private String createdTime;


    public QuestionsEntity() {
    }

    public QuestionsEntity(Long id, String question, String answer, String createdTime, String request, String response, String audioQuestionFileName, String audioAnswerFileName) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.createdTime = createdTime;
        this.request = request;
        this.response = response;
        this.audioQuestionFileName = audioQuestionFileName;
        this.audioAnswerFileName = audioAnswerFileName;
    }

    public QuestionsEntity(Long id, String question, String answer, String createdTime, String request, String response) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.createdTime = createdTime;
        this.request = request;
        this.response = response;
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

    @Override
    public String toString() {
        return "QuestionsEntity{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", audioQuestionFileName='" + audioQuestionFileName + '\'' +
                ", audioAnswerFileName='" + audioAnswerFileName + '\'' +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
