package com.example.order_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private String customerName;
    private String product;
    private Integer quantity;
    private Double totalPrice;
    private LocalDateTime createdAt;

}
