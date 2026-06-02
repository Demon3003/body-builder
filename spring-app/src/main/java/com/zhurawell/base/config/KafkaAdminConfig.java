package com.zhurawell.base.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@PropertySource("classpath:kafka.properties")
public class KafkaAdminConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String KAFKA_BOOTSTRAP_SERVER;

    @Bean("customKafkaAdmin")
    public KafkaAdmin kafkaAdmin() {
        log.info("Kafka Admin config initialized");
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVER);
        configs.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, "PLAINTEXT");
//        configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
//        configs.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
//        configs.put(SaslConfigs.SASL_JAAS_CONFIG, String.format(
//                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" " + "password=\"%s\";",  "sa", "000000"
//        ));
        return new KafkaAdmin(configs);
    }
}
