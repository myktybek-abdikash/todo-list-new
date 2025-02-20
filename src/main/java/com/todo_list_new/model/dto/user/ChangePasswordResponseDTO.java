package com.todo_list_new.model.dto.user;

public class ChangePasswordResponseDTO {
    private String message;

    public ChangePasswordResponseDTO(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
