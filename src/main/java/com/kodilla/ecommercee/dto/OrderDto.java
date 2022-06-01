package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long userId;
    private Long cartId;
    private LocalDateTime created;
    private OrderStatus orderStatus;

}
