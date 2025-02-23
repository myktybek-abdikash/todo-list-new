package com.todo_list_new.controller;

import com.todo_list_new.configuration.SecurityUtils;
import com.todo_list_new.model.dto.task.TaskRequestDTO;
import com.todo_list_new.model.dto.task.TaskResponseDTO;
import com.todo_list_new.model.dto.task.TaskWithUserResponseDTO;
import com.todo_list_new.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@Tag(name = "Task API", description = "API для управления задачами")
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
    @Operation(summary = "Создание задач", description = "Создает задачу с текущим авторизованным пользователем")
    @SecurityRequirement(name = "bearerAuth")
    public TaskWithUserResponseDTO createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.createTask(taskRequestDTO);
    }

    @GetMapping(MY_TASKS_PATH)
    @Operation(summary = "Получение задач", description = "Возвращает задачи текущего авторизованного пользователя")
    @SecurityRequirement(name = "bearerAuth")
    public List<TaskResponseDTO> getMyTasks() {
        String username = SecurityUtils.getCurrentUsername();
        return taskService.getTasksForUser(username);
    }

    @PatchMapping(UPDATE_BY_ID_PATH)
    @Operation(summary = "Обновление задач", description = "Обновляет задачу по ID")
    @SecurityRequirement(name = "bearerAuth")
    public TaskResponseDTO updateTask(@PathVariable int id, @RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.updateTask(id, taskRequestDTO);
    }

    @DeleteMapping(DELETE_BY_ID_PATH)
    @Operation(summary = "Удаление задач", description = "Удаляет задачу по ID")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }






}
