package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @GetMapping(value = "{cartId}")
    public CartDto getCart(@PathVariable Long cartId) {
        return new CartDto(1L, "user 1");
    }

    @GetMapping
    public List<CartDto> getCarts() {
        return new ArrayList<>();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto updateCart(@RequestBody CartDto cartDto) {
        return new CartDto(1L, "edited user 1");
    }

    @DeleteMapping(value = "{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
    }
}