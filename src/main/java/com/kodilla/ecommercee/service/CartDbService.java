package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDbService {

    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final ProductMapper productMapper;
    @Autowired
    private final ProductDbService productService;
    @Autowired
    private final ProductRepository productRepository;


    public Cart getNewCart(Long cartId) throws CartNotFoundException {
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);

    }

    public List<ProductDto> getAllProductsFromCart(Long cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        return productMapper.mapToProductDtoList(cart.getProducts());
    }

    public Cart addProductToCart(Long cartId, Long productId) throws ProductNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());
        if (productRepository.existsById(productId)) {
            cart.getProducts().add(productService.getProduct(productId));
            return cartRepository.save(cart);
        } else {
            throw new ProductNotFoundException();
        }
    }

    public boolean deleteProductFromCart(Long cartId, Long productId)
            throws CartNotFoundException, ProductNotFoundException {
            Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException());
            Product productToDelete = null;
            if (productRepository.existsById(productToDelete.getId())) {
                productToDelete = productService.getProduct(productId);
                getAllProductsFromCart(cartId).remove(productToDelete);
                cartRepository.save(cart);
            } else {
                throw new ProductNotFoundException();
            }

        return true;
    }

    public void deleteCart(final Long cartId) throws CartNotFoundException {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
        } else {
            throw new CartNotFoundException();
        }
    }
}