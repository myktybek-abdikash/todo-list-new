package com.todo_list_new.model.dto.task;

import com.todo_list_new.model.TaskStatus;

import java.time.LocalDate;

public class TaskResponseDTO {
    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate date;

    private TaskResponseDTO(Builder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.status = builder.status;
        this.date = builder.date;
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

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int id;
        private String title;
        private String description;
        private TaskStatus status;
        private LocalDate date;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder status(TaskStatus status){
            this.status = status;
            return this;
        }

        public Builder date(LocalDate date){
            this.date = date;
            return this;
        }

        public TaskResponseDTO build(){
            return new TaskResponseDTO(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", status=" + status +
                    ", date=" + date +
                    '}';
        }
    }


}
