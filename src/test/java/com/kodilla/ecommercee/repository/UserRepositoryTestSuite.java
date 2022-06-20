package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.dto.OrderStatus;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

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
        Long johnId = userRepository.save(john).getId();
        Long samId = userRepository.save(sam).getId();
        Long paulId = userRepository.save(paul).getId();
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
        Long johnId = userRepository.save(john).getId();
        cartRepository.save(cartOne);
        orderRepository.save(orderOne);
        User expectedJohn = userRepository.findById(johnId).get();

        //Then
        assertEquals(user.getId(), expectedJohn.getId());
        assertEquals(user.getName(), expectedJohn.getName());
        assertEquals(user.getSurname(), expectedJohn.getSurname());
        assertEquals(user.getUserKey(), expectedJohn.getUserKey());
        assertEquals(user.getOrders().size(), expectedJohn.getOrders().size());
        assertEquals(user.getCarts().size(), expectedJohn.getCarts().size());
        assertTrue(expectedJohn.isStatus());

        //Cleanup
        orderRepository.deleteById(orderOne.getId());
        cartRepository.deleteById(cartOne.getId());
        userRepository.deleteById(johnId);
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