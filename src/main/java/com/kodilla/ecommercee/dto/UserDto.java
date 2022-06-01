package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String surename;
    private int status;
    private int userKey;

}
