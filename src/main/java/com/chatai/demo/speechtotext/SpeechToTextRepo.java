package com.chatai.demo.speechtotext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeechToTextRepo extends JpaRepository<SpeechToTextEntity,Long> {}
