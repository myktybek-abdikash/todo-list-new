package com.todo_list_new.model.dto;

import com.todo_list_new.model.TaskStatus;

import java.time.LocalDate;

public class TaskWithUserResponseDTO {
    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate date;
    private UserResponseDTO userResponseDTO;

    private TaskWithUserResponseDTO(TaskWithUserResponseDTO.Builder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.status = builder.status;
        this.date = builder.date;
        this.userResponseDTO = builder.userResponseDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserResponseDTO getUserResponseDTO() {
        return userResponseDTO;
    }

    public void setUserResponseDTO(UserResponseDTO userResponseDTO) {
        this.userResponseDTO = userResponseDTO;
    }

    public static TaskWithUserResponseDTO.Builder builder(){
        return new TaskWithUserResponseDTO.Builder();
    }

    public static class Builder{
        private int id;
        private String title;
        private String description;
        private TaskStatus status;
        private LocalDate date;
        private UserResponseDTO userResponseDTO;

        public TaskWithUserResponseDTO.Builder id(int id){
            this.id = id;
            return this;
        }

        public TaskWithUserResponseDTO.Builder title(String title){
            this.title = title;
            return this;
        }

        public TaskWithUserResponseDTO.Builder description(String description){
            this.description = description;
            return this;
        }

        public TaskWithUserResponseDTO.Builder status(TaskStatus status){
            this.status = status;
            return this;
        }

        public TaskWithUserResponseDTO.Builder date(LocalDate date){
            this.date = date;
            return this;
        }

        public TaskWithUserResponseDTO.Builder userResponseDTO(UserResponseDTO userResponseDTO){
            this.userResponseDTO = userResponseDTO;
            return this;
        }

        public TaskWithUserResponseDTO build(){
            return new TaskWithUserResponseDTO(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", status=" + status +
                    ", date=" + date +
                    ", userResponseDTO=" + userResponseDTO +
                    '}';
        }
    }
}
