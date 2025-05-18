package com.pepsales.notification.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic notificationTopic() {
        return new NewTopic("notification_topic", 1, (short) 1);
    }
}
