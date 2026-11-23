package com.example.notifications.listener;

import com.example.notifications.entity.Notification;
import com.example.notifications.event.OrderCreatedEvent;
import com.example.notifications.repository.NotificationRepository;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@Component
public class OrderEventListener {

    private static final Logger log =
            LoggerFactory.getLogger(OrderEventListener.class);

    private final JsonMapper jsonMapper;
    private final NotificationRepository notificationRepository;

    public OrderEventListener(JsonMapper jsonMapper,
                              NotificationRepository notificationRepository) {
        this.jsonMapper = jsonMapper;
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "orders.created")
    public void onOrderCreated(String message) {
        OrderCreatedEvent event =
                jsonMapper.readValue(message, OrderCreatedEvent.class);
        log.info("Получено событие: заказ {} пользователя {}",
                event.orderId(), event.username());

        //7.1
        if (notificationRepository.existsByOrderId(event.orderId())) {
            log.warn("Повторное событие по заказу {}: пропускаем",
                    event.orderId());
            return;
        }

        simulateSending();

        String text = "Здравствуйте, " + event.username() + "! Заказ №"
                + event.orderId() + " (" + event.productName() + ", "
                + event.quantity() + " шт.) на сумму "
                + event.totalPrice() + " руб. принят.";
        notificationRepository.save(new Notification(
                event.orderId(), event.username(), text));
        log.info("Уведомление по заказу {} отправлено", event.orderId());
    }

    private void simulateSending() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}