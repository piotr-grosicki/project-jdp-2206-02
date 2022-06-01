package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

        @GetMapping
        public List<ProductDto> getProducts(){
            return new ArrayList<>();
        }

        @GetMapping(value = "{productId}")
        public ProductDto getProduct(@PathVariable Long productId) {
            return new ProductDto(1L, "name", 1.0);
        }

        @DeleteMapping(value = "{productId}")
        public void deleteProduct(@PathVariable Long productId){
        }

        @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ProductDto updateProduct(@RequestBody ProductDto productDto){
            return new ProductDto(1L, "updated name", 1.0);
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public void createProduct(@RequestBody ProductDto productDto){
        }
}