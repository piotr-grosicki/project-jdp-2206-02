package com.kodilla.ecommercee.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cartId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToMany(mappedBy = "carts")
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    @OneToOne(mappedBy = "cart")
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }
}
