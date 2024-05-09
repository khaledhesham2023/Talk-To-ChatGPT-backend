package com.chatai.demo.questiontoanswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionToAnswerRepo extends JpaRepository<QuestionToAnswerEntity,Long> {}
