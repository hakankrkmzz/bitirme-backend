package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDTO;
import com.example.demo.user.model.UserMapperImpl;
import com.example.demo.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserDTO addUser(UserDTO userDTO){
        User user = userRepository.save(UserMapperImpl.toEntity(userDTO));
        log.info(String.valueOf(user));
        return UserMapperImpl.toDTO(user);
    }

    public UserDTO findUserById(String id){
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            return UserMapperImpl.toDTO(user);
        }
        return null;
    }

    public Boolean deleteUserById(String id){
        userRepository.deleteById(id);
        if (findUserById(id) == null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for(User user: users){
            userDTOS.add(UserMapperImpl.toDTO(user));
        }
        return userDTOS;
    }

    public UserDTO findUserByPassword(String pass){
        User user = userRepository.findByPassword(pass);
        return UserMapperImpl.toDTO(user);
    }




}
