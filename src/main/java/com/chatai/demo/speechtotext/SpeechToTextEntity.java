package com.chatai.demo.speechtotext;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "speech_to_text_table",schema = "chataiapp")
public class SpeechToTextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stt_id",columnDefinition = "BIGINT")
    @JsonIgnore
    private Long sttId;

    @Column(name = "stt_question",columnDefinition = "LONGTEXT")
    private String question;

    @Column(name = "audio_question_filename",columnDefinition = "LONGTEXT")
    private String questionFileName;

    @Column(name = "stt_request_body",columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String requestBody;

    @Column(name = "stt_response_body",columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String responseBody;

    public SpeechToTextEntity(Long sttId, String question, String questionFileName, String requestBody, String responseBody) {
        this.sttId = sttId;
        this.question = question;
        this.questionFileName = questionFileName;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public Long getSttId() {
        return sttId;
    }

    public SpeechToTextEntity() {
    }

    @Override
    public String toString() {
        return "SpeechToTextEntity{" +
                "sttId=" + sttId +
                ", question='" + question + '\'' +
                ", questionFileName='" + questionFileName + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }

    public void setQuestionFileName(String questionFileName) {
        this.questionFileName = questionFileName;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public void setSttId(Long sttId) {
        this.sttId = sttId;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public String getQuestionFileName() {
        return questionFileName;
    }

    public String getRequestBody() {
        return requestBody;
    }
}