package com.todo_list_new.mapper;

import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.UserRequestDTO;
import com.todo_list_new.model.dto.UserResponseDTO;

public interface UserMapper {
    Users toEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toDTO(Users users);

}
