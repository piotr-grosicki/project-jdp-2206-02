package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    UserDbService userDbService;

    @Autowired
    CartRepository cartRepository;

    public Order mapToOrder(final OrderDto orderDto) throws UserNotFoundException {
        return Order.builder()
                .id(orderDto.getOrderId())
                .user(userDbService.getUser(orderDto.getUserId()))
                .cart(cartRepository.findById(orderDto.getCartId()).get())
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