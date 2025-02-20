package com.todo_list_new.controller;

import com.todo_list_new.configuration.SecurityUtils;
import com.todo_list_new.model.dto.task.TaskRequestDTO;
import com.todo_list_new.model.dto.task.TaskResponseDTO;
import com.todo_list_new.model.dto.task.TaskWithUserResponseDTO;
import com.todo_list_new.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public TaskWithUserResponseDTO createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.createTask(taskRequestDTO);
    }

    @GetMapping("/my-tasks")
    public List<TaskResponseDTO> getMyTasks() {
        String username = SecurityUtils.getCurrentUsername();
        return taskService.getTasksForUser(username);
    }

    @PatchMapping("/update/{id}")
    public TaskResponseDTO updateTask(@PathVariable int id, @RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.updateTask(id, taskRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }






}
