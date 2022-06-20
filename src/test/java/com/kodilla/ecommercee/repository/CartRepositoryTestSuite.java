package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Group;
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
        User user1 = User.builder()
                .id(1L)
                .name("userName1")
                .surname("userSurname1")
                .status(true)
                .userKey(1)
                .carts(new ArrayList<>())
                .build();
        User user2 = User.builder()
                .id(2L)
                .name("userName2")
                .surname("userSurname2")
                .status(true)
                .userKey(2)
                .carts(new ArrayList<>())
                .build();
        User user3 = User.builder()
                .id(3L)
                .name("userName3")
                .surname("userSurname3")
                .status(true)
                .userKey(3)
                .carts(new ArrayList<>())
                .build();
        Cart cart1 = Cart.builder().user(user1).build();
        Cart cart2 = Cart.builder().user(user2).build();
        Cart cart3 = Cart.builder().user(user3).build();

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

        //CleanUp
        cartRepository.deleteById(cart1.getId());
        cartRepository.deleteById(cart2.getId());
        cartRepository.deleteById(cart3.getId());

//        userRepository.deleteById(user1.getId());
//        userRepository.deleteById(user2.getId());
//        userRepository.deleteById(user3.getId());
    }

    @Test
    public void shouldReadCart() {
        //Given
        User user1 = User.builder()
                .id(1L)
                .name("userName1")
                .surname("userSurname1")
                .status(true)
                .userKey(1)
                .carts(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
        User user2 = User.builder()
                .id(2L)
                .name("userName2")
                .surname("userSurname2")
                .status(true)
                .userKey(2)
                .carts(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();

        Cart cart1 = Cart.builder().user(user1).build();
        Cart cart2 = Cart.builder().user(user2).build();

        user1.getCarts().add(cart1);
        user2.getCarts().add(cart2);

        //When
        userRepository.save(user1);
        userRepository.save(user2);

        cartRepository.save(cart1);
        cartRepository.save(cart2);

        List<Cart> testListCart = new ArrayList<>();
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
        Product product1 = Product.builder().name("product1").price(1.99).group(new Group()).build();
        Product product2 = Product.builder().name("product2").price(2.99).group(new Group()).build();
        Cart cart = new Cart();

        cart.getProducts().add(product1);

        cartRepository.save(cart);
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> testListProduct = new ArrayList<>();
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
        User user = new User();
        Cart cart = Cart.builder().user(user).build();

        //When
        cartRepository.save(cart);
        cartRepository.deleteById(cart.getId());

        //Then
        assertFalse(cartRepository.findById(cart.getId()).isPresent());
    }

    @Test
    public void shouldDeleteCartNotProduct() {
        //Given
        Product product1 = Product.builder().name("product1").price(1.99).group(Group.builder().name("testGroup").products(new ArrayList<>()).build()).build();
        Product product2 = Product.builder().name("product2").price(2.99).group(Group.builder().name("testGroup").products(new ArrayList<>()).build()).build();
        Cart cart = new Cart();

        cart.getProducts().add(product1);

        cartRepository.save(cart);
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> testListProduct = new ArrayList<>();
        testListProduct.add(product1);
        testListProduct.add(product2);

        // When
        Cart cartToUpdate = cartRepository.findById(cart.getId()).get();
        cartToUpdate.getProducts().add(product2);
        cartRepository.save(cartToUpdate);
        cartRepository.deleteById(cart.getId());

        //Then
        assertTrue(productRepository.existsById(product1.getId()));
        assertTrue(productRepository.existsById(product1.getId()));
    }
}
