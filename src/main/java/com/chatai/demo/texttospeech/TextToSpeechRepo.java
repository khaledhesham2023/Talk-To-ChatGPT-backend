package com.chatai.demo.texttospeech;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextToSpeechRepo extends JpaRepository<TextToSpeechEntity,Long> {}
