package com.example.notifications.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 500)
    private String message;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected Notification() {
    }

    public Notification(Long orderId, String username, String message) {
        this.orderId = orderId;
        this.username = username;
        this.message = message;
        this.createdAt = Instant.now();
    }

    // Геттеры и сеттеры сгенерируйте средствами IDE
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Long getOrderId() {return orderId;}
    public void setOrderId(Long orderId) {this.orderId = orderId;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

    public Instant getCreatedAt() {return createdAt;}
    public void setCreatedAt(Instant createdAt) {this.createdAt = createdAt;}
}