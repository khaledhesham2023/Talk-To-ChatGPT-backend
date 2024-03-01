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

    @Column(name = "voice_file_name", columnDefinition = "VARCHAR(255)")
    private String audioFileName;


    public QuestionsEntity() {
    }

    public QuestionsEntity(Long id, String question, String answer, String audioFileName) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.audioFileName = audioFileName;
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

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }

    public String getAudioFileName() {
        return audioFileName;
    }
}
