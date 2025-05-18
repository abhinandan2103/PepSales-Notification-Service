package com.pepsales.notification.service;

import com.pepsales.notification.dto.NotificationRequest;
import com.pepsales.notification.model.Notification;
import com.pepsales.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InAppNotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendInAppNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setMessage(request.getMessage());
        notification.setType("IN_APP");
        notification.setStatus("SENT");
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
        System.out.println("Saved in-app notification for user " + request.getUserId());
    }
}
