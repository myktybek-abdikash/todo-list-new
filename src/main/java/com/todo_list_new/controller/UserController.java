package com.todo_list_new.controller;

import com.todo_list_new.model.dto.user.*;
import com.todo_list_new.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static final String REGISTER_PATH = "/register";
    private static final String LOGIN_PATH = "/login";
    private static final String GET_BY_ID_PATH = "/get/{id}";
    private static final String UPDATE_BY_ID_PATH = "/update/{id}";
    private static final String CHANGE_PASSWORD_PATH = "/change-password";
    private static final String DELETE_BY_ID_PATH = "/delete/{id}";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(REGISTER_PATH)
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserResponseDTO response = userService.register(userRegistrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<String> login(@RequestBody UserRequestDTO userRequestDTO) {
        String response = userService.verify(userRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(GET_BY_ID_PATH)
    public ResponseEntity<UserResponseDTO> findById(@PathVariable int id) {
        UserResponseDTO response = userService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(UPDATE_BY_ID_PATH)
    public ResponseEntity<UserResponseDTO> updateById(@PathVariable int id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserResponseDTO response = userService.updateById(id, userUpdateDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(CHANGE_PASSWORD_PATH)
    public ResponseEntity<ChangePasswordResponseDTO> changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        ChangePasswordResponseDTO response = userService.changePassword(changePasswordRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(DELETE_BY_ID_PATH)
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
