package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public Order mapToOrder(final OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getOrderId())
                .user(orderDto.getUser())
                .cart(orderDto.getCart())
                .created(orderDto.getCreated())
                .orderStatus(orderDto.getOrderStatus())
                .build();
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser(),
                order.getCart(),
                order.getCreated(),
                order.getOrderStatus()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}