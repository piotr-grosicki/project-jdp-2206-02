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

    //@ManyToOne
    @JoinColumn(name = "userId")
    private Long userId;

    //@OneToOne
    @JoinColumn(name = "cartId")
    private Long cartId;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    public Order(Long userId, Long cartId, OrderStatus orderStatus) {
        this.userId = userId;
        this.cartId = cartId;
        this.orderStatus = orderStatus;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}