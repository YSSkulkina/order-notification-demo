package com.example.notification_service.service;

import com.example.order_service.event.OrderCreatedEvent;
import com.example.notification_service.model.Notification;
import com.example.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "order-created", groupId = "notification-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent: {}", event);

        // Сохраняем уведомление в БД
        Notification notification = new Notification();
        notification.setOrderId(event.getOrderId());
        notification.setCustomerName(event.getCustomerName());
        notification.setMessage(String.format("Order #%d created for %s: %s x%d - $%.2f",
                event.getOrderId(),
                event.getCustomerName(),
                event.getProduct(),
                event.getQuantity(),
                event.getTotalPrice()
        ));
        notification.setReceivedAt(LocalDateTime.now());
        notification.setProcessed(true);

        notificationRepository.save(notification);

        // Также выводим в консоль
        System.out.println("📧 NOTIFICATION: " + notification.getMessage());
    }

}
