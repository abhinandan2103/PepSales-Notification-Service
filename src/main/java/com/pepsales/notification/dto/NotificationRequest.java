package com.pepsales.notification.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private String userId;
    private String message;
    private String type; // "EMAIL", "SMS", "IN_APP"
}

