package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDbService service;
    private final UserMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = mapper.mapToUser(userDto);
        service.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/updateStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUserStatus(@RequestBody UserDto userDto) throws UserNotFoundException {
        User user = mapper.mapToUser(userDto);
        User modifiedUserStatus = service.changeUserStatus(user.getId());
        User savedUser = service.saveUser(modifiedUserStatus);
        return ResponseEntity.ok(mapper.mapToUserDto(savedUser));
    }

    @PutMapping(value = "/updateUserKey", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUserKey(@RequestBody UserDto userDto)  throws UserNotFoundException{
        User user = mapper.mapToUser(userDto);
        User modifiedUserKey = service.changeUserKey(user.getId());
        User savedUser = service.saveUser(modifiedUserKey);
        return ResponseEntity.ok(mapper.mapToUserDto(savedUser));
    }
}
