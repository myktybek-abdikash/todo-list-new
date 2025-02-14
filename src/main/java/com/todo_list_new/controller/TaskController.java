package com.todo_list_new.controller;

import com.todo_list_new.configuration.SecurityUtils;
import com.todo_list_new.model.Task;
import com.todo_list_new.model.dto.TaskRequestDTO;
import com.todo_list_new.model.dto.TaskResponseDTO;
import com.todo_list_new.model.dto.TaskWithUserResponseDTO;
import com.todo_list_new.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.gelAllTasks();
    }

    @PostMapping("/create")
    public TaskWithUserResponseDTO createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.createTask(taskRequestDTO);
    }

    @GetMapping("/my-tasks")
    public List<TaskResponseDTO> getMyTasks() {
        String username = SecurityUtils.getCurrentUsername();
        return taskService.getTasksForUser(username);
    }

}
