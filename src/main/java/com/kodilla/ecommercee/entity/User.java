package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "userId", unique = true)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "status")
    private boolean status;

    @NotNull
    @Column(name = "userKey")
    private int userKey;

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Cart.class,
            mappedBy = "user"
    )
    private List<Cart> carts = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user"
    )
    private List<Order> orders = new ArrayList<>();

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }
}
