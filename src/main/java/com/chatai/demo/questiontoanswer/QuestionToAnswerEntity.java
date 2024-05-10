package com.chatai.demo.questiontoanswer;

import com.chatai.demo.speechtotext.SpeechToTextEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "question_to_answer_table",schema = "chataiapp")
public class QuestionToAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qta_id",columnDefinition = "BIGINT")
    @JsonIgnore
    private Long qtaId;

    @Column(name = "qta_answer",columnDefinition = "LONGTEXT")
    private String answer;

    @Column(name = "qta_request_body",columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String request;

    @Column(name = "qta_response_body",columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String response;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sttId",referencedColumnName = "stt_id")
    @JsonIgnore
    private SpeechToTextEntity speechToTextEntity;

    public QuestionToAnswerEntity() {
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getQtaId() {
        return qtaId;
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    public String getAnswer() {
        return answer;
    }

    public QuestionToAnswerEntity(Long qtaId, String answer, String request, String response, SpeechToTextEntity speechToTextEntity) {
        this.qtaId = qtaId;
        this.answer = answer;
        this.request = request;
        this.response = response;
        this.speechToTextEntity = speechToTextEntity;
    }

    @Override
    public String toString() {
        return "QuestionToAnswerEntity{" +
                "qtaId=" + qtaId +
                ", answer='" + answer + '\'' +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                ", speechToTextEntity='" + speechToTextEntity + '\'' +
                '}';
    }
}
