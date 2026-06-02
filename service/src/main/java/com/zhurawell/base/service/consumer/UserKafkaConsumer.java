package com.zhurawell.base.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserKafkaConsumer {

//    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 1000), dltTopicSuffix = ".DLT")
//    @KafkaListener(topics = "users", groupId = "user-service")
//    public void processOrder(CreateUserMessage user) {
//        log.debug("Received User Message: {}", user);
//    }
}
