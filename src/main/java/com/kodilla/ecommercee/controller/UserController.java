package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUserStatus(@RequestBody UserDto userDto){
        return new UserDto(1L, "test name","test surename" , 0, 12345);
    }

    @PutMapping(value = "/userkey", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUserKey(@RequestBody UserDto userDto){
        return new UserDto(1L, "test name","test surename", 1, 12314);
    }



}
