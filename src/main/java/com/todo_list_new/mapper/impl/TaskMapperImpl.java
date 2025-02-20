package com.todo_list_new.mapper.impl;

import com.todo_list_new.mapper.TaskMapper;
import com.todo_list_new.model.Task;
import com.todo_list_new.model.TaskStatus;
import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.task.TaskRequestDTO;
import com.todo_list_new.model.dto.task.TaskResponseDTO;
import com.todo_list_new.model.dto.task.TaskWithUserResponseDTO;
import com.todo_list_new.model.dto.user.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task updatedTask(Task task, TaskRequestDTO taskRequestDTO) {
        LocalDate date = LocalDate.now();
        TaskStatus status;
        try {
            status = TaskStatus.valueOf(taskRequestDTO.getStatus());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный статус задачи");
        }
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(status);
        task.setDate(date);
        return task;
    }

    public Task toEntityWithUser(TaskRequestDTO taskRequestDTO, Users user) {
        LocalDate date = LocalDate.now();
        TaskStatus status;
        try {
            status = TaskStatus.valueOf(taskRequestDTO.getStatus());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный статус задачи");
        }
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(status);
        task.setDate(date);
        task.setUser(user);
        return task;
    }



    public TaskWithUserResponseDTO toDTOWithUser(Task task) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(task.getUser().getId());
        userResponseDTO.setName(task.getUser().getName());
        userResponseDTO.setEmail(task.getUser().getEmail());

        return TaskWithUserResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .date(task.getDate())
                .userResponseDTO(userResponseDTO)
                .build();
    }

    @Override
    public TaskResponseDTO toDTO(Task task){
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .date(task.getDate())
                .build();
    }


}
