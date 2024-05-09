package com.chatai.demo.questiontoanswer;

import com.chatai.demo.speechtotext.SpeechToTextEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "question_to_answer_table",schema = "chataiapp")
public class QuestionToAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qta_id",columnDefinition = "BIGINT")
    private Long qtaId;

    @Column(name = "qta_answer",columnDefinition = "LONGTEXT")
    private String answer;

    @Column(name = "qta_request_body",columnDefinition = "LONGTEXT")
    private String request;

    @Column(name = "qta_response_body",columnDefinition = "LONGTEXT")
    private String response;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sttId",referencedColumnName = "stt_id")
    private SpeechToTextEntity speechToTextEntity;


    public void setQtaId(Long qtaId) {
        this.qtaId = qtaId;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public void setSpeechToTextEntity(SpeechToTextEntity speechToTextEntity) {
        this.speechToTextEntity = speechToTextEntity;
    }

    public SpeechToTextEntity getSpeechToTextEntity() {
        return speechToTextEntity;
    }

    public QuestionToAnswerEntity(Long qtaId, String answer, String request, String response, SpeechToTextEntity speechToTextEntity) {
        this.qtaId = qtaId;
        this.answer = answer;
        this.request = request;
        this.response = response;
        this.speechToTextEntity = speechToTextEntity;
    }

    public QuestionToAnswerEntity() {
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
