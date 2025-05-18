package com.pepsales.notification.controller;

import com.pepsales.notification.dto.NotificationRequest;
import com.pepsales.notification.model.Notification;
import com.pepsales.notification.repository.NotificationRepository;
import com.pepsales.notification.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    NotificationRepository notificationRepository;

    @PostMapping
    public String sendNotification(@RequestBody NotificationRequest request) {
        if (request.getType() == null) {
            return "Notification type is required!";
        }

        kafkaProducerService.sendNotification(request);

        return "Notification request submitted to Kafka successfully!";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Notification Service is running!";
    }

    @GetMapping("/notifications/{userId}")
    public List<Notification> getNotifications(@PathVariable String userId) {
        return notificationRepository.findByUserId(userId);
    }
}
