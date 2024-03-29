package com.chatai.demo.questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepo extends JpaRepository<QuestionsEntity,Long> {

    QuestionsEntity findByAudioQuestionFileName(String audioName);

}
