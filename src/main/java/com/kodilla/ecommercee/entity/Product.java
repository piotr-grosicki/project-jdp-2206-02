package com.kodilla.ecommercee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productId")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private Double price;

    public Product(String name, Double price, Group group) {
        this.name = name;
        this.price = price;
        this.group = group;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "groupId")
    private Group group;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "productsInCart",
            joinColumns = {@JoinColumn(name = "productId", referencedColumnName = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "cartId", referencedColumnName = "cartId")}
    )
    private List<Cart> carts;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
