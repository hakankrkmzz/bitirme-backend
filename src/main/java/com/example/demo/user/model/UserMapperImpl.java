package com.example.demo.user.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserMapperImpl implements Serializable {

    public static UserDTO toDTO(User user){
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFullName(user.getFullName());

        return userDTO;
    }

    public static User toEntity(UserDTO userDTO){
        if (userDTO == null) {
            return null;
        }

        User user = new User();

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());

        return user;
    }
}
