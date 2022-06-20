package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;


    public Cart mapToCart(final CartDto cartDto) {
        if (cartDto.getId() == null) {
            return new Cart();
        } else {
            return Cart.builder()
                    .id(cartDto.getId())
                    .products(cartDto.getProductId().stream()
                            .map(e -> productRepository.findById(e)
                                    .orElseGet(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()))
                    .user(userRepository.findById(cartDto.getId()).orElseThrow(
                            () -> new IllegalArgumentException("Client not found")))
                    .build();

        }
    }

    public CartDto mapToCartDto(final Cart cart) {
        if (cart.getId() == null) {
            throw new IllegalArgumentException("Cart doesn't exist");
        } else {
            return new CartDto(
                    cart.getId(),
                    cart.getUser().getId(),
                    cart.getProducts().stream()
                            .map(e -> e.getId())
                            .collect(Collectors.toList())
            );
        }

    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }

    }