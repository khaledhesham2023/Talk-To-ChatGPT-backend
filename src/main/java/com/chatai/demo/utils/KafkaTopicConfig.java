package com.chatai.demo.utils;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    // Url of Kafka Server (localhost:9092)
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Autowired
    private OpenAIConfig openAIConfig;


    /**
     * Responsible for creating the Kafka Topic to which messages will be sent into.
     * @return Kafka admin instance
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    /**
     * Create new topic if not exists otherwise returns the existed topic.
     * @return the created/existing topic
     */
    @Bean
    public NewTopic chatOpenAITopic() {
        return new NewTopic(openAIConfig.getTopicName(), 1, (short) 1);
    }
}
