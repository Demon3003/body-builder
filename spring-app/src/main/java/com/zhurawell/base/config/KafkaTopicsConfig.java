package com.zhurawell.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class KafkaTopicsConfig {

//    @Autowired
//    @Qualifier("customKafkaAdmin")
//    private KafkaAdmin admin;
//
//    @Bean
//    public String userCrud() {
//        log.info("Kafka TopicBuilder initialized");
//        admin.createOrModifyTopics(TopicBuilder
//                .name("users")
//                .partitions(2)
//                .replicas(1)
//                .compact()
//                .build());
//        return "1";
//    }
//
//    @Bean
//    public NewTopic trackingCrud() {
//        return TopicBuilder
//                .name("tracking")
//                .partitions(2)
//                .replicas(1)
//                .config("min.insync.replicas", "1")
//                .config("retention.ms", String.valueOf(30L * 24 * 60 * 60 * 1000L)) // 30 дней
//                .compact()
//                .build();
//    }

}
