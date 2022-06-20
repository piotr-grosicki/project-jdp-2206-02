package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .status(userDto.isStatus())
                .userKey(userDto.getUserKey())
                .build();
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.isStatus(),
                user.getUserKey()
        );
    }
}
