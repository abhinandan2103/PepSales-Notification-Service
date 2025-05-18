package com.pepsales.notification.service;


import com.pepsales.notification.dto.NotificationRequest;
import com.pepsales.notification.dto.NotificationResponse;
import com.pepsales.notification.model.Notification;
import com.pepsales.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationResponse processNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());
        notification.setStatus("PENDING");
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);

        // You can add RabbitMQ publishing later here if needed
        return new NotificationResponse("Notification created successfully",null);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Notification> updateStatus(Long id, String status) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notification.setStatus(status);
                    return notificationRepository.save(notification);
                });
    }
}

