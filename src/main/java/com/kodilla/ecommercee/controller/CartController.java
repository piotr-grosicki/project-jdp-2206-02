package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/carts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getNewCart(cartId);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @GetMapping(value = "{cartId}/products")
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable Long cartId) {
        List <ProductDto> productsFromCartList = new ArrayList<>();
        try {
            productsFromCartList = cartService.getAllProductsFromCart(cartId);
        } catch (CartNotFoundException e) {
            System.out.println("Cart doesn't exist");
        }
        return ResponseEntity.ok(productsFromCartList);
    }

//    @PostMapping(consumes = APPLICATION_JSON_VALUE)
//    public ResponseEntity<Long> createCart(@RequestBody Long userId) {
//        Cart cart = cartService.getNewCart(userId);
//        return ResponseEntity.ok(cart.getId());
//    }

    @PostMapping (value = "/{cartId}/products")
    public ResponseEntity<CartDto> addProduct(@PathVariable Long cartId, @RequestBody Long productId) {
        Cart cart = cartService.addProductToCart(cartId, productId);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @DeleteMapping(value = "/{cartId}/products/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.deleteProductFromCart(cartId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cartId}")
    public ResponseEntity<Void>  deleteCart(@PathVariable Long cartId) {
        try {
            cartService.deleteCart(cartId);
        } catch (CartNotFoundException e) {
            System.out.println("Cart doesn't exist");
        }
        return ResponseEntity.ok().build();
    }
}