package com.todo_list_new.service;

import com.todo_list_new.configuration.SecurityUtils;
import com.todo_list_new.exception.TaskNotFoundException;
import com.todo_list_new.exception.UserNotFoundException;
import com.todo_list_new.mapper.TaskMapper;
import com.todo_list_new.model.Task;
import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.task.TaskRequestDTO;
import com.todo_list_new.model.dto.task.TaskResponseDTO;
import com.todo_list_new.model.dto.task.TaskWithUserResponseDTO;
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

    public TaskWithUserResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        String username = SecurityUtils.getCurrentUsername();
        Users user = userRepository.findByName(username).orElseThrow(() ->
                new UserNotFoundException("User not found with username: " + username));

        Task task = taskMapper.toEntityWithUser(taskRequestDTO, user);
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

    public TaskResponseDTO updateTask(int id, TaskRequestDTO taskRequestDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new TaskNotFoundException("Task not found with id: " + id));
        taskMapper.updatedTask(task, taskRequestDTO);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
}
