package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.dto.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    //@ManyToOne
    @JoinColumn(name = "user_id")
    private Long userId;

    //@OneToOne
    @JoinColumn(name = "cart_id")
    private Long cartId;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "order_status")
    private OrderStatus orderStatus;
}