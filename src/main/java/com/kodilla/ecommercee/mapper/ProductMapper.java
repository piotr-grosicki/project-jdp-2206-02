package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public Product mapToProduct(final ProductDto productDto){
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
    }

    public ProductDto mapToProductDto(final Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList){
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
