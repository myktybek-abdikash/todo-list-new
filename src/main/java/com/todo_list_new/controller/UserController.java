package com.todo_list_new.controller;

import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.UserLoginDTO;
import com.todo_list_new.model.dto.UserRegistrationDTO;
import com.todo_list_new.model.dto.UserResponseDTO;
import com.todo_list_new.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.register(userRegistrationDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.verify(userLoginDTO);
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        userService.deleteById(id);
    }



    @GetMapping("/info")
    public String info() {
        return "Welcome to my list";
    }
}
