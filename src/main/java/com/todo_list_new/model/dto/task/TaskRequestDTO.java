package com.todo_list_new.model.dto.task;

import com.todo_list_new.model.dto.user.UserResponseDTO;

public class TaskRequestDTO {
    private String title;
    private String description;
    private String status;
    private UserResponseDTO userResponseDTO;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public UserResponseDTO getUserResponseDTO() {
        return userResponseDTO;
    }
}
