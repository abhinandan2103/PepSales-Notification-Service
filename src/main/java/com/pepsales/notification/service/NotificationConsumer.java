package com.pepsales.notification.service;


import com.pepsales.notification.dto.NotificationRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class NotificationConsumer {

    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private InAppNotificationService inAppService;
    public void receive(NotificationRequest request) {
        if (request.getType() == null) {
            System.out.println("Notification type is null, sending default EMAIL notification.");
            emailService.sendEmail(
                    request.getUserId(),
                    "Notification from Pepsales",   // default subject
                    request.getMessage()
            );  // default fallback
            return;
        }

        switch (request.getType()) {
            case "EMAIL" -> emailService.sendEmail(
                    request.getUserId(),
                    "Notification from Pepsales",
                    request.getMessage()
            );
            case "SMS" -> smsService.sendSms(request);
            case "IN_APP" -> inAppService.sendInAppNotification(request);
            default -> {
                System.out.println("Unknown notification type! Sending default EMAIL notification.");
                emailService.sendEmail(
                        request.getUserId(),
                        "Notification from Pepsales",
                        request.getMessage()
                );  // fallback for unknown type
            }
        }
    }

}

