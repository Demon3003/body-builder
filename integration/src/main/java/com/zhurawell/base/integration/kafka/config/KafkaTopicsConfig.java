package com.zhurawell.base.integration.kafka.config;

import com.zhurawell.base.integration.kafka.constants.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicsConfig {

    @Autowired
    private KafkaAdmin admin;

    @Bean
    public NewTopic userCrud() {
        return TopicBuilder
                .name(Topics.USER_CRUD_TOPIC)
                .partitions(2)
                .replicas(1)
                .compact()
                .build();
    }

}
