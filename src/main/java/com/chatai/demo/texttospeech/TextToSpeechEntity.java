package com.chatai.demo.texttospeech;

import com.chatai.demo.questiontoanswer.QuestionToAnswerEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "text_to_speech_table", schema = "chataiapp")
public class TextToSpeechEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tts_id", columnDefinition = "BIGINT")
    private Long ttsId;

    @Column(name = "audio_answer_filename", columnDefinition = "LONGTEXT")
    private String answerFilename;

    @Column(name = "tts_request_body", columnDefinition = "LONGTEXT")
    private String request;

    @Column(name = "tts_response_body", columnDefinition = "LONGTEXT")
    private String response;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qtaId", referencedColumnName = "qta_id")
    private QuestionToAnswerEntity questionToAnswerEntity;

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
}
