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
    @Column(name = "userId", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "status")
    private boolean status;

    @Column(name = "userKey")
    private int userKey;

    public User(String name, String surname, boolean status, int userKey){
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.userKey = userKey;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname= surname;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }
}
