package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.OrderStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")

public class OrderController {

    @GetMapping
    public List<OrderDto> getOrder() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return new OrderDto(1L, 1L, 1L,  LocalDateTime.of(2022,
                Month.JUNE, 01, 12, 30, 40), OrderStatus.PENDING );
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createOrder(@RequestBody OrderDto orderDto) {
        return 1L;
    }
}