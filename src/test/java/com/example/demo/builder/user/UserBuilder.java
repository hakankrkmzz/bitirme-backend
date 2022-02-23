package com.example.demo.builder.user;

import com.example.demo.user.model.UserDTO;

public class UserBuilder {

    private UserDTO userDTO = new UserDTO();

    public UserBuilder() {}

    public UserBuilder(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserBuilder buildSomeDummy() {

        userDTO.setFullName("dummyname");
        userDTO.setUsername("dummyusername");
        userDTO.setPassword("123456");

        return this;
    }

    public UserDTO build() {
        return userDTO;
    }
}
