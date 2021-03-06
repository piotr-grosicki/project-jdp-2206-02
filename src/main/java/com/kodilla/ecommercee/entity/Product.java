package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @ManyToMany
    @JoinTable(
            name = "productsInCart",
            joinColumns = {@JoinColumn(name = "productId", referencedColumnName = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "cartId", referencedColumnName = "cartId")}
    )
    private List<Cart> carts = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
