package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/carts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {

    private final CartDbService cartDbService;
    private final CartMapper cartMapper;

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartDbService.getNewCart(cartId);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @GetMapping(value = "{cartId}/products")
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable Long cartId) throws CartNotFoundException {
        List <ProductDto> productsFromCartList = new ArrayList<>();
        try {
            productsFromCartList = cartDbService.getAllProductsFromCart(cartId);
        } catch (CartNotFoundException e) {}
        return ResponseEntity.ok(productsFromCartList);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createCart(@RequestBody Long cartId) {
        Cart cart = null;
        try {
            cart = cartDbService.getNewCart(cartId);
        } catch (CartNotFoundException e) {
        }
        return ResponseEntity.ok(cart.getId());
    }

    @PostMapping (value = "/{cartId}/products/{productId}")
    public ResponseEntity<CartDto> addProduct(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartDbService.addProductToCart(cartId, productId);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @DeleteMapping(value = "/{cartId}/products/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        try {
            cartDbService.deleteProductFromCart(cartId, productId);
        } catch (CartNotFoundException e) {}
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cartId}")
    public ResponseEntity<Void>  deleteCart(@PathVariable Long cartId) throws CartNotFoundException {
        try {
            cartDbService.deleteCart(cartId);
        } catch (CartNotFoundException e) {}
        return ResponseEntity.ok().build();
    }
}