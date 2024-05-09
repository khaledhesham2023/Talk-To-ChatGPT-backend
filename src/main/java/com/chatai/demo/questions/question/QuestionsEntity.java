package com.chatai.demo.questions.question;

import com.chatai.demo.questiontoanswer.QuestionToAnswerEntity;
import com.chatai.demo.speechtotext.SpeechToTextEntity;
import com.chatai.demo.texttospeech.TextToSpeechEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "questions", schema = "chataiapp")
public class QuestionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sttId", referencedColumnName = "stt_id")
    private SpeechToTextEntity speechToTextEntity;

    @ManyToOne
    @JoinColumn(name = "qtaId",referencedColumnName = "qta_id")
    private QuestionToAnswerEntity questionToAnswerEntity;

    @ManyToOne
    @JoinColumn(name = "ttsId",referencedColumnName = "tts_id")
    private TextToSpeechEntity textToSpeechEntity;

    @Column(name = "created_time",columnDefinition = "VARCHAR(255)")
    private String createdTime;

    public QuestionsEntity(Long id, SpeechToTextEntity speechToTextEntity, QuestionToAnswerEntity questionToAnswerEntity, TextToSpeechEntity textToSpeechEntity, String createdTime) {
        this.id = id;
        this.speechToTextEntity = speechToTextEntity;
        this.questionToAnswerEntity = questionToAnswerEntity;
        this.textToSpeechEntity = textToSpeechEntity;
        this.createdTime = createdTime;
    }

    public QuestionsEntity() {
    }

    @Override
    public String toString() {
        return "QuestionsEntity{" +
                "id=" + id +
                ", speechToTextEntity=" + speechToTextEntity +
                ", questionToAnswerEntity=" + questionToAnswerEntity +
                ", textToSpeechEntity=" + textToSpeechEntity +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
