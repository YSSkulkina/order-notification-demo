package com.example.order_service.service;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.event.OrderCreatedEvent;
import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String ORDER_TOPIC = "order-created";

    @Transactional
    public Order createOrder(OrderRequest request) {
        // Создаём заказ
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setProduct(request.getProduct());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(request.getQuantity() * request.getPrice());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("CREATED");

        Order savedOrder = orderRepository.save(order);
        log.info("Order saved with id: {}", savedOrder.getId());

        // Отправляем событие в Kafka
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getCustomerName(),
                savedOrder.getProduct(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice(),
                savedOrder.getCreatedAt()
        );

        kafkaTemplate.send(ORDER_TOPIC, event);
        log.info("OrderCreatedEvent sent to Kafka for order id: {}", savedOrder.getId());

        return savedOrder;
    }

}
