package com.example.notifications.controller;

import com.example.notifications.repository.NotificationRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository repository;

    public NotificationController(NotificationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<NotificationResponse> getAll() {
        return repository.findAll().stream()
                .map(n -> new NotificationResponse(n.getId(),
                        n.getOrderId(), n.getUsername(), n.getMessage(),
                        n.getCreatedAt()))
                .toList();
    }
}