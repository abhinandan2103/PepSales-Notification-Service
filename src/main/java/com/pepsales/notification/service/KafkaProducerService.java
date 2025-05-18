package com.pepsales.notification.service;

import com.pepsales.notification.dto.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, NotificationRequest> kafkaTemplate;

    public void sendNotification(NotificationRequest request) {
        kafkaTemplate.send("notification_topic", request);
        System.out.println("Notification pushed to Kafka topic.");
    }
}
