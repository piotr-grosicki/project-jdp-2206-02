package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CartRepositoryTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldCreateCart() {

        //Given
        User user1 = new User("user1", "user1", true, 00001);
        User user2 = new User("user2", "user2", true, 00002);
        User user3 = new User("user3", "user3", true, 00003);


        Cart cart1 = new Cart(user1);
        Cart cart2 = new Cart(user2);
        Cart cart3 = new Cart(user3);

        user1.getCarts().add(cart1);
        user2.getCarts().add(cart2);
        user3.getCarts().add(cart3);

        //When
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);


        //Then

        assertTrue(cartRepository.findById(cart1.getId()).isPresent());
        assertTrue(cartRepository.findById(cart2.getId()).isPresent());
        assertTrue(cartRepository.findById(cart3.getId()).isPresent());

        cartRepository.deleteById(cart1.getId());
        cartRepository.deleteById(cart2.getId());
        cartRepository.deleteById(cart3.getId());

        userRepository.deleteById(user1.getId());
        userRepository.deleteById(user2.getId());
        userRepository.deleteById(user3.getId());
    }

    @Test
    public void shouldReadCart() {
        //Given
        User user1 = new User("user1", "user1", true, 00001);
        User user2 = new User("user2", "user2", true, 00002);

        Cart cart1 = new Cart(user1);
        Cart cart2 = new Cart(user2);

        user1.getCarts().add(cart1);
        user2.getCarts().add(cart2);

        //When
        userRepository.save(user1);
        userRepository.save(user2);

        cartRepository.save(cart1);
        cartRepository.save(cart2);

        List testListCart = new ArrayList<Cart>();
        testListCart.add(cart1);
        testListCart.add(cart2);

        //Then
        assertEquals(cartRepository.findAll(), testListCart);

        //CleanUp
        cartRepository.deleteById(cart1.getId());
        cartRepository.deleteById(cart2.getId());

        userRepository.deleteById(user1.getId());
        userRepository.deleteById(user2.getId());
    }

    @Test
    public void shouldUpdateCart() {
        //Given
        Product product1 = new Product("product1", 1.99);
        Product product2 = new Product("product2", 2.99);
        Cart cart = new Cart();

        cart.getProducts().add(product1);

        cartRepository.save(cart);
        productRepository.save(product1);
        productRepository.save(product2);

        List testListProduct = new ArrayList<Product>();
        testListProduct.add(product1);
        testListProduct.add(product2);

        // When
        Cart cartToUpdate = cartRepository.findById(cart.getId()).get();
        cartToUpdate.getProducts().add(product2);
        cartRepository.save(cartToUpdate);

        //Then
        assertEquals(testListProduct, cartRepository.findById(cart.getId()).get().getProducts());
        assertTrue(cartRepository.findById(cart.getId()).get().getProducts().containsAll(testListProduct));

        //CleanUp
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
        cartRepository.deleteById(cart.getId());
    }

    @Test
    public void shouldDeleteCart() {
        //Given
        User user1 = new User();
        Cart cart1 = new Cart(user1);

        //When
        cartRepository.save(cart1);
        cartRepository.deleteById(cart1.getId());

        //Then
        assertFalse(cartRepository.findById(cart1.getId()).isPresent());

    }

}



