package com.todo_list_new.mapper;

import com.todo_list_new.model.Task;
import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.task.TaskRequestDTO;
import com.todo_list_new.model.dto.task.TaskResponseDTO;
import com.todo_list_new.model.dto.task.TaskWithUserResponseDTO;

public interface TaskMapper {

    Task updatedTask(Task task, TaskRequestDTO taskRequestDTO);

    Task toEntityWithUser(TaskRequestDTO taskRequestDTO, Users user);

    TaskWithUserResponseDTO toDTOWithUser(Task task);

    TaskResponseDTO toDTO(Task task);
}
