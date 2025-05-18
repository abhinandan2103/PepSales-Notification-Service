package com.pepsales.notification.model;


import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String message;
    private String type;
    private String status;
    private LocalDateTime createdAt;
}

