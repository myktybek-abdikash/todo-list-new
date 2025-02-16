package com.todo_list_new.service;

import com.todo_list_new.configuration.SecurityUtils;
import com.todo_list_new.exception.UserNotFoundException;
import com.todo_list_new.mapper.TaskMapper;
import com.todo_list_new.model.Task;
import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.TaskRequestDTO;
import com.todo_list_new.model.dto.TaskResponseDTO;
import com.todo_list_new.model.dto.TaskWithUserResponseDTO;
import com.todo_list_new.repository.TaskRepository;
import com.todo_list_new.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    public List<Task> gelAllTasks() {
        return taskRepository.findAll();
    }

    public TaskWithUserResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        String username = SecurityUtils.getCurrentUsername();
        Users user = userRepository.findByName(username).orElseThrow(() ->
                new UserNotFoundException("User not found with username: " + username));

        Task task = taskMapper.toEntity(taskRequestDTO, user);
        taskRepository.save(task);
        return taskMapper.toDTOWithUser(task);
    }

    public List<TaskResponseDTO> getTasksForUser(String username) {
        Users user = userRepository.findByName(username).orElseThrow(() ->
                new UserNotFoundException("User not found with username: " + username));

        List<Task> tasks = taskRepository.findByUser(user);

        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());

    }
}
