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
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void shouldCreateCart() {
        //Given

        User user1 = User.builder()
                .name("userName1")
                .surname("userSurname1")
                .status(true)
                .userKey(1)
                .carts(new ArrayList<>())
                .build();
        User user2 = User.builder()
                .name("userName2")
                .surname("userSurname2")
                .status(true)
                .userKey(2)
                .carts(new ArrayList<>())
                .build();
        User user3 = User.builder()
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
        Long user1Id = userRepository.save(user1).getId();
        Long user2Id = userRepository.save(user2).getId();
        Long user3Id = userRepository.save(user3).getId();

        Long cart1Id = cartRepository.save(cart1).getId();
        Long cart2Id = cartRepository.save(cart2).getId();
        Long cart3Id = cartRepository.save(cart3).getId();

        //Then
        assertTrue(cartRepository.findById(cart1Id).isPresent());
        assertTrue(cartRepository.findById(cart2Id).isPresent());
        assertTrue(cartRepository.findById(cart3Id).isPresent());

        //CleanUp
        userRepository.deleteById(user1Id);
        userRepository.deleteById(user2Id);
        userRepository.deleteById(user3Id);

        cartRepository.deleteById(cart1Id);
        cartRepository.deleteById(cart2Id);
        cartRepository.deleteById(cart3Id);
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
        Long user1Id = userRepository.save(user1).getId();
        Long user2Id = userRepository.save(user2).getId();

        Long cart1Id = cartRepository.save(cart1).getId();
        Long cart2Id = cartRepository.save(cart2).getId();

        List<Cart> testListCart = new ArrayList<>();
        testListCart.add(cart1);
        testListCart.add(cart2);

        //Then
        assertEquals(cartRepository.findAll(), testListCart);

        //CleanUp
        userRepository.deleteById(user1Id);
        userRepository.deleteById(user2Id);

        cartRepository.deleteById(cart1Id);
        cartRepository.deleteById(cart2Id);
    }

    @Test
    public void shouldUpdateCart() {
        //Given
        Product product1 = Product.builder().name("product1").price(1.99).group(Group.builder().name("testGroup").products(new ArrayList<>()).build()).build();
        Product product2 = Product.builder().name("product2").price(2.99).group(Group.builder().name("testGroup").products(new ArrayList<>()).build()).build();
        Cart cart = Cart.builder().build();



        cart.getProducts().add(product1);

        Long cartId = cartRepository.save(cart).getId();
        Long product1Id = productRepository.save(product1).getId();
        Long product2Id = productRepository.save(product2).getId();

        List<Product> testListProduct = new ArrayList<>();
        testListProduct.add(product1);
        testListProduct.add(product2);

        // When
        Cart cartToUpdate = cartRepository.findById(cartId).get();
        cartToUpdate.getProducts().add(product2);
        cartRepository.save(cartToUpdate);

        //Then
        assertEquals(testListProduct, cartRepository.findById(cart.getId()).get().getProducts());


        //CleanUp
        productRepository.deleteById(product1Id);
        productRepository.deleteById(product2Id);
        cartRepository.deleteById(cartId);
    }

    @Test
    public void shouldDeleteCart() {
        //Given

        User user = new User();
        Cart cart = Cart.builder().user(user).build();

        //When
        Long cartId = cartRepository.save(cart).getId();
        cartRepository.deleteById(cartId);

        //Then
        assertFalse(cartRepository.findById(cartId).isPresent());
    }

    @Test
    public void shouldDeleteCartNotProduct() {
        //Given

        Product product1 = Product.builder().name("product1").price(1.99).group(Group.builder().name("testGroup").products(new ArrayList<>()).build()).build();
        Product product2 = Product.builder().name("product2").price(2.99).group(Group.builder().name("testGroup").products(new ArrayList<>()).build()).build();

        Cart cart = new Cart();

        cart.getProducts().add(product1);

        Long cartId = cartRepository.save(cart).getId();
        Long product1Id = productRepository.save(product1).getId();
        Long product2Id = productRepository.save(product2).getId();

        List testListProduct = new ArrayList<Long>();
        testListProduct.add(product1.getId());
        testListProduct.add(product2.getId());

        // When
        Cart cartToUpdate = cartRepository.findById(cartId).get();
        cartToUpdate.getProducts().add(product2);
        cartRepository.save(cartToUpdate);
        cartRepository.deleteById(cart.getId());
        groupRepository.save(product1.getGroup());
        groupRepository.save(product2.getGroup());


        //Then
        assertTrue(productRepository.existsById(product1.getId()));
        assertTrue(productRepository.existsById(product1.getId()));
    }
}
