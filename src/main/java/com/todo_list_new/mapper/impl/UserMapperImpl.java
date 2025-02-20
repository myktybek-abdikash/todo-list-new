package com.todo_list_new.mapper.impl;

import com.todo_list_new.mapper.UserMapper;
import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.user.UserRequestDTO;
import com.todo_list_new.model.dto.user.UserResponseDTO;
import com.todo_list_new.model.dto.user.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users toEntity(UserRequestDTO userRequestDTO) {
        Users user = new Users();
        user.setName(userRequestDTO.getName());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        return user;
    }

    @Override
    public Users toUpdatedEntity(UserUpdateDTO userUpdateDTO, Users user) {
        user.setName(userUpdateDTO.getName());
        user.setEmail(userUpdateDTO.getEmail());
        return user;
    }

    @Override
    public UserResponseDTO toDTO(Users user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
