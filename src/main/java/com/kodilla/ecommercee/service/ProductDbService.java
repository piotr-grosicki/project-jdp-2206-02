package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(final Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public void deleteProduct(final Long productId) throws ProductNotFoundException{
        if(productRepository.existsById(productId)){
            productRepository.deleteById(productId);
        } else {
            throw new ProductNotFoundException();
        }
    }

    public Product saveProduct(final Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(final Product product) throws ProductNotFoundException{
        if(productRepository.existsById(product.getId())){
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException();
        }
    }
}
