package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private User user;
    private Cart cart;
    private LocalDateTime created;
    private OrderStatus orderStatus;
}