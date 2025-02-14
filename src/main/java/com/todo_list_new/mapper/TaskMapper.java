package com.todo_list_new.mapper;

import com.todo_list_new.model.Task;
import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.TaskRequestDTO;
import com.todo_list_new.model.dto.TaskResponseDTO;
import com.todo_list_new.model.dto.TaskWithUserResponseDTO;

public interface TaskMapper {
    Task toEntity(TaskRequestDTO taskRequestDTO, Users user);

    TaskWithUserResponseDTO toDTOWithUser(Task task);

    TaskResponseDTO toDTO(Task task);
}
