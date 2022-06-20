package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;
    private Long userId;
    private List<ProductDto> products;
}