package com.example.first.controllers;

import com.example.first.request.UserRequest;
import com.example.first.response.UserResponse;
import com.example.first.services.UserService;
import com.example.first.shred.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping
    public String getUsers(){
        return "get users was called";
    }
    @PostMapping
    public UserResponse creatUsers(@RequestBody UserRequest userRequest){
        UserDto userDto = new UserDto();// couche presentation
        BeanUtils.copyProperties(userRequest,userDto); // couche presentation

        UserDto createUser = userService.createUser(userDto); //

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(createUser,userResponse);
        return userResponse;

    }
    @PutMapping
    public String updateUsers(){
        return "update users was called";
    }
    @DeleteMapping
    public String deleteUsers(){
        return "delete users was called";
    }
}
