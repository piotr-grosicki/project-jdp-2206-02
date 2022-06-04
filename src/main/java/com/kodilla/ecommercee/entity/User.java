package com.kodilla.ecommercee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Entity(name = "users")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public User(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname= surname;
    }
}
