package com.example.notifications.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(
        Long orderId,
        String username,
        String productName,
        int quantity,
        BigDecimal totalPrice
) {
}