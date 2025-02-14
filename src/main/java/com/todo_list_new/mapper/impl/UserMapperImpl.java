package com.todo_list_new.mapper.impl;

import com.todo_list_new.mapper.UserMapper;
import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.UserRequestDTO;
import com.todo_list_new.model.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public Users toEntity(UserRequestDTO userRequestDTO) {
        Users user = new Users();
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
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
