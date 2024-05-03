package com.chatai.demo.questions.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface QuestionsRepo extends JpaRepository<QuestionsEntity,Long> {

}
