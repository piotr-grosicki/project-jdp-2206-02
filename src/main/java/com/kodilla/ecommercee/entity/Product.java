package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

//    @ManyToOne
//    @JoinColumn(name = "groupId")
//    private Group group;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "productsInCart",
//            joinColumns = {@JoinColumn(name = "productId", referencedColumnName = "productId")},
//            inverseJoinColumns = {@JoinColumn(name = "cartId", referencedColumnName = "cartId")}
//    )
//    private List<Cart> carts;
}
