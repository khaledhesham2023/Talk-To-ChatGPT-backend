package com.chatai.demo.texttospeech;

import com.chatai.demo.questiontoanswer.QuestionToAnswerEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "text_to_speech_table", schema = "chataiapp")
public class TextToSpeechEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tts_id", columnDefinition = "BIGINT")
    @JsonIgnore
    private Long ttsId;

    @Column(name = "audio_answer_filename", columnDefinition = "LONGTEXT")
    private String answerFilename;

    @Column(name = "tts_request_body", columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String request;

    @Column(name = "tts_response_body", columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String response;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qtaId", referencedColumnName = "qta_id")
    @JsonIgnore
    private QuestionToAnswerEntity questionToAnswerEntity;

    public TextToSpeechEntity() {
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setRequest(String request) {
        this.request = request;
    }


    public String getRequest() {
        return request;
    }

    public Long getTtsId() {
        return ttsId;
    }


    public TextToSpeechEntity(Long ttsId, String answerFilename, String request, String response, QuestionToAnswerEntity questionToAnswerEntity) {
        this.ttsId = ttsId;
        this.answerFilename = answerFilename;
        this.request = request;
        this.response = response;
        this.questionToAnswerEntity = questionToAnswerEntity;
    }

    @Override
    public String toString() {
        return "TextToSpeechEntity{" +
                "ttsId=" + ttsId +
                ", answerFilename='" + answerFilename + '\'' +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                ", questionToAnswerEntity=" + questionToAnswerEntity + '\'' +
                '}';
    }

    public void setQuestionToAnswerEntity(QuestionToAnswerEntity questionToAnswerEntity) {
        this.questionToAnswerEntity = questionToAnswerEntity;
    }

    public void setAnswerFilename(String answerFilename) {
        this.answerFilename = answerFilename;
    }

    public void setTtsId(Long ttsId) {
        this.ttsId = ttsId;
    }

    public QuestionToAnswerEntity getQuestionToAnswerEntity() {
        return questionToAnswerEntity;
    }

    public String getAnswerFilename() {
        return answerFilename;
    }
}
