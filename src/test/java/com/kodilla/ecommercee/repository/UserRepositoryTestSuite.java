package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.dto.OrderStatus;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAllTest() {
        //Given

        User user1 = User.builder()
                .name("userName1")
                .surname("userSurname1")
                .status(true)
                .userKey(1)
                .carts(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
        User user2 = User.builder()
                .name("userName2")
                .surname("userSurname2")
                .status(true)
                .userKey(2)
                .carts(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
        User user3 = User.builder()
                .name("userName3")
                .surname("userSurname3")
                .status(true)
                .userKey(3)
                .carts(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
        Cart cartOne = Cart.builder().user(user1).build();
        Order orderOne = new Order(user1, cartOne, OrderStatus.PENDING);
        Cart cartTwo = Cart.builder().user(user2).build();
        Order orderTwo = new Order(user2, cartTwo, OrderStatus.COMPLETED);
        Cart cartThree = Cart.builder().user(user3).build();
        Order orderThree = new Order(user3, cartThree, OrderStatus.CANCELLED);
        user1.getCarts().add(cartOne);
        user1.getOrders().add(orderOne);
        user2.getCarts().add(cartTwo);
        user2.getOrders().add(orderTwo);
        user3.getCarts().add(cartThree);
        user3.getOrders().add(orderThree);

        //When
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        Long user1Id = user1.getId();
        Long user2Id = user2.getId();
        Long user3Id = user3.getId();
        List<User> userList = userRepository.findAll();

        //Then
        assertEquals(3, userList.size());

        //Cleanup
        userRepository.deleteById(user1Id);
        userRepository.deleteById(user2Id);
        userRepository.deleteById(user3Id);
    }

    @Test
    void findByIdTest() {
        //Given

        User user = User.builder()
                .name("userName1")
                .surname("userSurname1")
                .status(true)
                .userKey(1)
                .carts(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
        Cart cartOne = Cart.builder().user(user).build();
        Order orderOne = new Order(user, cartOne, OrderStatus.PENDING);
        user.getCarts().add(cartOne);
        user.getOrders().add(orderOne);

        //When
        userRepository.save(user);
        Long userId = user.getId();
        User expectedJohn = userRepository.findById(userId).get();

        //Then
        assertEquals(user.getId(), expectedJohn.getId());
        assertEquals(user.getName(), expectedJohn.getName());
        assertEquals(user.getSurname(), expectedJohn.getSurname());
        assertEquals(user.getUserKey(), expectedJohn.getUserKey());
        assertEquals(user.getOrders().size(), expectedJohn.getOrders().size());
        assertEquals(user.getCarts().size(), expectedJohn.getCarts().size());
        assertTrue(expectedJohn.isStatus());

        //Cleanup
        userRepository.deleteById(userId);
    }

    @Test
    void deleteUserTest(){
        //Given

        User user = User.builder()
                .name("userName1")
                .surname("userSurname1")
                .status(true)
                .userKey(1)
                .carts(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
        Cart cartOne = Cart.builder().user(user).build();
        Order orderOne = new Order(user, cartOne, OrderStatus.PENDING);
        user.getCarts().add(cartOne);
        user.getOrders().add(orderOne);

        //When
        userRepository.save(user);
        Long johnId = user.getId();
        userRepository.delete(user);
        Optional<User> expectedNotJohn = userRepository.findById(johnId);

        //Then
        assertFalse(expectedNotJohn.isPresent());
    }
}
