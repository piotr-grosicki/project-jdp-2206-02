package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.dto.OrderStatus;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testAddNewOrder() {
        //Given
        User user = new User("John", "Smith", true, 1);
        Cart cart = new Cart(user);
        Order order = new Order(user, cart, OrderStatus.PENDING);

        //When
        User testUser = userRepository.save(user);
        Cart testCart = cartRepository.save(cart);
        Order testOrder = orderRepository.save(order);

        Long testOrderId = testOrder.getId();

        //Then
        assertTrue(orderRepository.existsById(testOrderId));

        //Cleanup
        orderRepository.deleteById(testOrderId);
        cartRepository.deleteById(testCart.getId());
        userRepository.deleteById(testUser.getId());
    }

    @Test
    public void testUpdateOrderDeliveryStatus() {
        //Given
        User user = new User("John", "Smith", true, 1);
        Cart cart = new Cart(user);
        Order order = new Order(user, cart, OrderStatus.PENDING);

        //When
        User testUser = userRepository.save(user);
        Cart testCart = cartRepository.save(cart);
        Order testOrder = orderRepository.save(order);
        testOrder.setOrderStatus(OrderStatus.IN_DELIVERY);
        orderRepository.save(testOrder);

        Long testOrderId = testOrder.getId();

        //Then
        assertEquals(OrderStatus.IN_DELIVERY, orderRepository.findById(testOrderId).get().getOrderStatus());

        //Cleanup
        orderRepository.deleteById(testOrderId);
        cartRepository.deleteById(testCart.getId());
        userRepository.deleteById(testUser.getId());
    }

    @Test
    public void testCreateOrderRelationshipWithUserEntity() {
        //Given
        User user = new User("John", "Smith", true, 1);
        Cart cart = new Cart(user);
        Order order = new Order(user, cart, OrderStatus.PENDING);

        //When
        User testUser = userRepository.save(user);
        Cart testCart = cartRepository.save(cart);
        Order testOrder = orderRepository.save(order);

        Long testUserId = testUser.getId();
        Long testOrderId = testOrder.getId();

        //Then
        assertEquals(1, userRepository.findById(testUserId).get().getOrders().size());
        assertEquals(testOrderId, userRepository.findById(testUserId).get().getOrders().get(0).getId());
        assertEquals(testUserId, orderRepository.findById(testOrderId).get().getUser().getId());

        //Cleanup
        orderRepository.deleteById(testOrderId);
        cartRepository.deleteById(testCart.getId());
        userRepository.deleteById(testUserId);
    }

    @Test
    public void testCreateMultipleOrdersRelationshipWithUserEntity() {
        //Given
        User user = new User("John", "Smith", true, 1);
        Cart cart1 = new Cart(user);
        Cart cart2 = new Cart(user);
        Cart cart3 = new Cart(user);
        Order order1 = new Order(user, cart1, OrderStatus.PENDING);
        Order order2 = new Order(user, cart2, OrderStatus.IN_DELIVERY);
        Order order3 = new Order(user, cart3, OrderStatus.COMPLETED);

        //When
        User testUser = userRepository.save(user);
        Cart testCart1 = cartRepository.save(cart1);
        Cart testCart2 = cartRepository.save(cart2);
        Cart testCart3 = cartRepository.save(cart3);
        Order testOrder1 = orderRepository.save(order1);
        Order testOrder2 = orderRepository.save(order2);
        Order testOrder3 = orderRepository.save(order3);

        Long testUserId = testUser.getId();

        //Then
        assertEquals(3, userRepository.findById(testUserId).get().getOrders().size());

        //Cleanup
        orderRepository.deleteById(testOrder1.getId());
        orderRepository.deleteById(testOrder2.getId());
        orderRepository.deleteById(testOrder3.getId());
        cartRepository.deleteById(testCart1.getId());
        cartRepository.deleteById(testCart2.getId());
        cartRepository.deleteById(testCart3.getId());
        userRepository.deleteById(testUserId);
    }

    @Test
    public void testCreateOrderRelationshipWithCartEntity() {
        //Given
        User user = new User("John", "Smith", true, 1);
        Cart cart = new Cart(user);
        Order order = new Order(user, cart, OrderStatus.PENDING);

        //When
        User testUser = userRepository.save(user);
        Cart testCart = cartRepository.save(cart);
        Order testOrder = orderRepository.save(order);

        Long testCartId = testCart.getId();
        Long testOrderId = testOrder.getId();

        //Then
        assertEquals(testOrderId, cartRepository.findById(testCartId).get().getOrder().getId());
        assertEquals(testCartId, orderRepository.findById(testOrderId).get().getCart().getId());

        //Cleanup
        orderRepository.deleteById(testOrderId);
        cartRepository.deleteById(testCartId);
        userRepository.deleteById(testUser.getId());
    }

    @Test
    public void testDeleteOrder() {
        //Given
        User user = new User("John", "Smith", true, 1);
        Cart cart = new Cart(user);
        Order order = new Order(user, cart, OrderStatus.PENDING);

        //When
        User testUser = userRepository.save(user);
        Cart testCart = cartRepository.save(cart);
        Order testOrder = orderRepository.save(order);

        Long testOrderId = testOrder.getId();

        orderRepository.deleteById(testOrderId);

        //Then
        assertFalse(orderRepository.existsById(testOrderId));

        //Cleanup
        cartRepository.deleteById(testCart.getId());
        userRepository.deleteById(testUser.getId());
    }

    @Test
    public void testDeleteOrderDoesNotDeleteUser() {
        //Given
        User user = new User("John", "Smith", true, 1);
        Cart cart = new Cart(user);
        Order order = new Order(user, cart, OrderStatus.PENDING);

        //When
        User testUser = userRepository.save(user);
        Cart testCart = cartRepository.save(cart);
        Order testOrder = orderRepository.save(order);

        Long testOrderId = testOrder.getId();
        Long testUserId = testUser.getId();

        orderRepository.deleteById(testOrderId);

        //Then
        assertTrue(userRepository.existsById(testUserId));

        //Cleanup
        cartRepository.deleteById(testCart.getId());
        userRepository.deleteById(testUserId);
    }

    @Test
    public void testDeleteOrderDoesNotDeleteCart() {
        //Given
        User user = new User("John", "Smith", true, 1);
        Cart cart = new Cart(user);
        Order order = new Order(user, cart, OrderStatus.PENDING);

        //When
        User testUser = userRepository.save(user);
        Cart testCart = cartRepository.save(cart);
        Order testOrder = orderRepository.save(order);

        Long testOrderId = testOrder.getId();
        Long testCartId = testCart.getId();

        orderRepository.deleteById(testOrderId);

        //Then
        assertTrue(cartRepository.existsById(testCartId));

        //Cleanup
        cartRepository.deleteById(testCartId);
        userRepository.deleteById(testUser.getId());
    }
}