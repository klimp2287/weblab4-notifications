package com.example.notifications.controller;

import java.time.Instant;

public record NotificationResponse(
        Long id,
        Long orderId,
        String username,
        String message,
        Instant createdAt
) {
}