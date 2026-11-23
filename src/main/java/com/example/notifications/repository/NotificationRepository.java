package com.example.notifications.repository;

import com.example.notifications.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {
    //7.1
    boolean existsByOrderId(Long orderId);
}