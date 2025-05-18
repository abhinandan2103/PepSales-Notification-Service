package com.pepsales.notification.service;

import com.pepsales.notification.dto.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private InAppNotificationService inAppService;

    @KafkaListener(topics = "notification_topic", groupId = "notification_group")
    public void consumeNotification(NotificationRequest request) {
        System.out.println("Received notification from Kafka: " + request);

        try {
            switch (request.getType()) {
                case "EMAIL" -> emailService.sendEmail(
                        request.getUserId(), "Notification from Pepsales", request.getMessage()
                );
                case "SMS" -> smsService.sendSms(request);
                case "IN_APP" -> inAppService.sendInAppNotification(request);
                default -> System.out.println("Unknown notification type.");
            }
        } catch (Exception e) {
            System.out.println("Error processing notification: " + e.getMessage());
            // Optionally, add a retry logic here or push to a retry topic
        }
    }
}
