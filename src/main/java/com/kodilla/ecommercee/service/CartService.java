package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final CartMapper cartMapper;
    @Autowired
    private final ProductMapper productMapper;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final UserService userService;


    public Cart getNewCart(Long userId) {
        Cart cart = null;
        try {
            cart = Cart.builder().user(userService.getUser(userId)).build();
            cartRepository.save(cart);
        }
        catch (UserNotFoundException e) {
            System.out.println("User not found");
        }
        return cart;
    }

    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public List<ProductDto> getAllProductsFromCart(Long cartId) throws CartNotFoundException{
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        return productMapper.mapToProductDtoList(cart.getProducts());
    }

    public Cart addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());
        try {
            cart.getProducts().add(productService.getProduct(productId));
        } catch (ProductNotFoundException e) {
            System.out.println("Product not found");
        }
        return cartRepository.save(cart);
    }

    public boolean deleteProductFromCart(Long cartId, Long productId) {
        cartRepository.existsById(cartId);
            Cart cart = cartRepository.findById(cartId).orElse(new Cart());
            Product productToDelete = null;
            try {
                productToDelete = productService.getProduct(productId);
            } catch (ProductNotFoundException e) {
                System.out.println("Product not found");          }
            try {
                getAllProductsFromCart(cartId).remove(productToDelete);
            } catch (CartNotFoundException e) {
                System.out.println("Cart doesn't exist");}
            cartMapper.mapToCartDto(cartRepository.save(cart));
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