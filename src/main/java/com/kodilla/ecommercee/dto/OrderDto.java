package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long userId;
    private Long cartId;
    private LocalDateTime created;
    private OrderStatus orderStatus;
}