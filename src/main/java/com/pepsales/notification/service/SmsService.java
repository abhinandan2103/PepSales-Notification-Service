package com.pepsales.notification.service;

import com.pepsales.notification.dto.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendSms(NotificationRequest request) {
        String url = "https://textbelt.com/text";

        Map<String, String> body = new HashMap<>();
        body.put("phone", request.getUserId());
        body.put("message", request.getMessage());
        body.put("key", "textbelt");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        System.out.println("SMS API response: " + response.getBody());
    }
}
