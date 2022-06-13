package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@SpringBootTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private ProductRepository productRepository;

    @Test
    public void shouldCreateCart() {

        //Given
        User user1 = new User("user1", "user1", true, 00001);
        User user2 = new User("user2", "user2", true, 00002);
        User user3 = new User("user3", "user3", true, 00003);
//
//        Product product1 = new Product("product1", 1.99);
//        Product product2 = new Product("product2", 2.99);
//        Product product3 = new Product("product3", 3.99);
//        Product product4 = new Product("product4", 4.99);
//
//        List<Product> productList = new ArrayList<>();
//        productList.add(product1);
//        productList.add(product2);

        Cart cart1 = new Cart(user1);
        Cart cart2 = new Cart(user2);
        Cart cart3 = new Cart(user3);

        //When
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);

        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);

        user1.getCarts().add(cart1);
        user2.getCarts().add(cart2);
        user3.getCarts().add(cart3);

        Long user1Id = user1.getId();
        Long user2Id = user2.getId();
//        List<User> userList = userRepository.findAll();



//        productRepository.save(product1);
//        productRepository.save(product2);
//        productRepository.save(product3);
//        productRepository.save(product4);

        //Then

//        assertEquals(2, userList.size());

//        assertTrue(cartRepository.findById(cart1.getId()).isPresent());
//        assertTrue(cartRepository.findById(cart2.getId()).isPresent());
//        assertTrue(cartRepository.findById(cart3.getId()).isPresent());

        //CleanUp
//        productRepository.deleteById(product1.getId());
//        productRepository.deleteById(product2.getId());
//        productRepository.deleteById(product3.getId());
//        productRepository.deleteById(product4.getId());
//
//        cartRepository.deleteById(cart1.getId());
//        cartRepository.deleteById(cart2.getId());
//        cartRepository.deleteById(cart3.getId());
//
//        userRepository.deleteById(user1.getId());
//        userRepository.deleteById(user2.getId());
//        userRepository.deleteById(user3.getId());
    }

//    @Test
//    public void shouldDisplayCart() {
//        //Given
//        User user = new User();
//        Product product = new Product();
//        Cart cart = new Cart();
//
//        //When
//        userRepository.save(user);
//        cartRepository.save(cart);
}
