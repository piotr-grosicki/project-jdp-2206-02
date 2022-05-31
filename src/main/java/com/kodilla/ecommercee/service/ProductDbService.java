package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductsRepository productsRepository;

    public List<Product> getAllProducts(){
        return productsRepository.findAll();
    }

    public Product getProduct(final Long productId) throws ProductNotFoundException{
        return productsRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public void deleteProduct(final Long productId){
        productsRepository.deleteById(productId);
    }

    public Product saveProduct(final Product product){
        return productsRepository.save(product);
    }
}
