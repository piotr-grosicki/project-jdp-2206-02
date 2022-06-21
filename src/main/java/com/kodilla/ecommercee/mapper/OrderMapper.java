package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    UserDbService userDbService;
    CartService cartDbService;

    public Order mapToOrder(final OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        return Order.builder()
                .id(orderDto.getOrderId())
                .user(userDbService.getUser(orderDto.getUserId()))
                .cart(cartDbService.getNewCart(orderDto.getCartId()))
                .created(orderDto.getCreated())
                .orderStatus(orderDto.getOrderStatus())
                .build();
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                order.getCart().getId(),
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