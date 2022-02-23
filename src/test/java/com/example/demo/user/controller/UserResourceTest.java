package com.example.demo.user.controller;

import com.example.demo.builder.user.UserBuilder;
import com.example.demo.user.model.UserDTO;
import com.example.demo.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
class UserResourceTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void addUser() throws Exception {
        UserDTO userDTO = new UserBuilder()
                .buildSomeDummy()
                .build();

        String jsonUser = objectMapper.writeValueAsString(userDTO);

        ResultActions resultActions = this.mockMvc
                .perform(post("/app/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String batu = mvcResult.getResponse().getContentAsString();

        UserDTO savedUser = objectMapper.readValue(batu,UserDTO.class);
        Assertions.assertNotNull(savedUser.getId());

    }

    @Test
    void getAllUsers() {
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void getUserById() throws Exception {
        UserDTO userDTO = new UserBuilder()
                .buildSomeDummy()
                .build();
        UserDTO savedUser = userService.addUser(userDTO);

        ResultActions resultActions = this.mockMvc
                .perform(get("/app/user/get/" + savedUser.getId()))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        UserDTO resultUser = objectMapper.readValue(contentAsString,UserDTO.class);
        Assertions.assertEquals(savedUser.getId(),resultUser.getId());
    }
}