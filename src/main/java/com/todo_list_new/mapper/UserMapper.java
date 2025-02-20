package com.todo_list_new.mapper;

import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.user.UserRequestDTO;
import com.todo_list_new.model.dto.user.UserResponseDTO;
import com.todo_list_new.model.dto.user.UserUpdateDTO;

public interface UserMapper {
    Users toEntity(UserRequestDTO userRequestDTO);

    Users toUpdatedEntity(UserUpdateDTO userUpdateDTO, Users user);

    UserResponseDTO toDTO(Users users);

}
