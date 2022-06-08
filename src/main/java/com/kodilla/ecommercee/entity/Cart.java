package com.kodilla.ecommercee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
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

    public Cart(User user) {
        this.user = user;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "carts")
    private List<Product> products;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;
}
