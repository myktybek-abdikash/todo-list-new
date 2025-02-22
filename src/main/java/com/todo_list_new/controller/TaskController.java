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
@RequestMapping("/api/v1/task")
public class TaskController {

    private static final String CREATE_PATH = "/create";
    private static final String MY_TASKS_PATH = "/my-tasks";
    private static final String UPDATE_BY_ID_PATH = "/update/{id}";
    private static final String DELETE_BY_ID_PATH = "/delete/{id}";

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(CREATE_PATH)
    public TaskWithUserResponseDTO createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.createTask(taskRequestDTO);
    }

    @GetMapping(MY_TASKS_PATH)
    public List<TaskResponseDTO> getMyTasks() {
        String username = SecurityUtils.getCurrentUsername();
        return taskService.getTasksForUser(username);
    }

    @PatchMapping(UPDATE_BY_ID_PATH)
    public TaskResponseDTO updateTask(@PathVariable int id, @RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.updateTask(id, taskRequestDTO);
    }

    @DeleteMapping(DELETE_BY_ID_PATH)
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }






}
