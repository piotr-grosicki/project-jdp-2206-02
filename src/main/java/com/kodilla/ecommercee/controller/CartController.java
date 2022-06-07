package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @GetMapping(value = "{cartId}")
    public CartDto getCart(@PathVariable Long cartId) {
        return new CartDto(1L, "user 1", new ArrayList<>(Arrays.asList("product 1", "product 2")));
    }

    @GetMapping
    public List<CartDto> getCarts() {
        return new ArrayList<>();
    }

    @PostMapping(value = "/create/{userId}")
    public void createCart(@PathVariable Long userId) {
    }

    @PutMapping(value = "/add_product/{cartId}/{productId}")
    public CartDto addProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        return new CartDto(1L, "edited user 1", new ArrayList<>(Arrays.asList("product 1")));
    }

    @DeleteMapping(value = "/remove_product/{cartId}/{productId}")
    public void removeProduct(@PathVariable Long cartId, @PathVariable Long productId) {
    }

    @DeleteMapping(value = "{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
    }
}