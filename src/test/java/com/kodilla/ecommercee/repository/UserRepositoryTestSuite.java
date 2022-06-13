package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.dto.OrderStatus;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.User;
import org.junit.Assert;
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

    @Test
    void findAllTest() {
        //Given
        User john = new User("john", "smith", true, 12345);
        Cart cartOne = new Cart(john);
        Order orderOne = new Order(john, cartOne, OrderStatus.PENDING);
        User sam = new User("sam", "smith", true, 54321);
        Cart cartTwo = new Cart(sam);
        Order orderTwo = new Order(sam, cartTwo, OrderStatus.COMPLETED);
        User paul = new User("paul", "smith", true, 12565);
        Cart cartThree = new Cart(paul);
        Order orderThree = new Order(paul, cartThree, OrderStatus.CANCELLED);
        john.getCarts().add(cartOne);
        john.getOrders().add(orderOne);
        sam.getCarts().add(cartTwo);
        sam.getOrders().add(orderTwo);
        paul.getCarts().add(cartThree);
        paul.getOrders().add(orderThree);

        //When
        userRepository.save(john);
        userRepository.save(sam);
        userRepository.save(paul);
        Long johnId = john.getId();
        Long samId = sam.getId();
        Long paulId = paul.getId();
        List<User> userList = userRepository.findAll();

        //Then
        assertEquals(3, userList.size());

        //Cleanup
        userRepository.deleteById(johnId);
        userRepository.deleteById(samId);
        userRepository.deleteById(paulId);
    }

    @Test
    void findByIdTest() {
        //Given
        User john = new User("john", "smith", true, 12345);
        Cart cartOne = new Cart(john);
        Order orderOne = new Order(john, cartOne, OrderStatus.PENDING);
        john.getCarts().add(cartOne);
        john.getOrders().add(orderOne);

        //When
        userRepository.save(john);
        Long johnId = john.getId();
        User expectedJohn = userRepository.findById(johnId).get();

        //Then
        assertEquals(john.getId(), expectedJohn.getId());
        assertEquals(john.getName(), expectedJohn.getName());
        assertEquals(john.getSurname(), expectedJohn.getSurname());
        assertEquals(john.getUserKey(), expectedJohn.getUserKey());
        assertEquals(john.getOrders().size(), expectedJohn.getOrders().size());
        assertEquals(john.getCarts().size(), expectedJohn.getCarts().size());
        assertTrue(expectedJohn.isStatus());

        //Cleanup
        userRepository.deleteById(johnId);
    }

    @Test
    void deleteUserTest(){
        //Given
        User john = new User("john", "smith", true, 12345);
        Cart cartOne = new Cart(john);
        Order orderOne = new Order(john, cartOne, OrderStatus.PENDING);
        john.getCarts().add(cartOne);
        john.getOrders().add(orderOne);

        //When
        userRepository.save(john);
        Long johnId = john.getId();
        userRepository.delete(john);
        Optional<User> expectedNotJohn = userRepository.findById(johnId);

        //Then
        Assert.assertFalse(expectedNotJohn.isPresent());
    }
}