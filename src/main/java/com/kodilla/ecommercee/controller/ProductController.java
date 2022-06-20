package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/product")
public class ProductController {

        private final ProductDbService service;
        private final ProductMapper productMapper;

        @GetMapping
        public ResponseEntity<List<ProductDto>> getProducts(){
            List<Product> products = service.getAllProducts();
            return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
        }

        @GetMapping(value = "{productId}")
        public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) throws ProductNotFoundException {
            return ResponseEntity.ok(productMapper.mapToProductDto(service.getProduct(productId)));
        }

        @DeleteMapping(value = "{productId}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws ProductNotFoundException{
                service.deleteProduct(productId);
                return ResponseEntity.ok().build();
        }

        @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws ProductNotFoundException{
            Product product = productMapper.mapToProduct(productDto);
            Product savedProduct = service.updateProduct(product);
            return ResponseEntity.ok(productMapper.mapToProductDto(savedProduct));
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto){
                Product product = productMapper.mapToProduct(productDto);
                service.saveProduct(product);
                return ResponseEntity.ok().build();
        }
}