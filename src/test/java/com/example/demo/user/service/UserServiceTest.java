package com.example.demo.user.service;

import com.example.demo.builder.user.UserBuilder;
import com.example.demo.user.model.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureDataMongo
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void addUser() {

        UserDTO userDTO = new UserBuilder()
                .buildSomeDummy()
                .build();

        System.out.println(userDTO);
        UserDTO savedUser = userService.addUser(userDTO);
        System.out.println(savedUser);
        Assertions.assertNotEquals(null,savedUser.getId());


    }
}