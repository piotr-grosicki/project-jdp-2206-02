package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.dto.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "orderId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId")
    private Cart cart;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "orderStatus")
    private OrderStatus orderStatus;

    public Order(User user, Cart cart, OrderStatus orderStatus) {
        this.user = user;
        this.cart = cart;
        this.orderStatus = orderStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}