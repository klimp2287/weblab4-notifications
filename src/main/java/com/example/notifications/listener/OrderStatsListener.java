package com.example.notifications.listener;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderStatsListener {

    private static final Logger log =
            LoggerFactory.getLogger(OrderStatsListener.class);

    private final AtomicInteger counter = new AtomicInteger();

    @KafkaListener(topics = "orders.created", groupId = "analytics")
    public void onOrderCreated(String message) {
        log.info("[analytics] Всего заказов: {}",
                counter.incrementAndGet());
    }
}