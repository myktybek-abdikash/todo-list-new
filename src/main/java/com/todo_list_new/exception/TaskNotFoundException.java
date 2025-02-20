package com.todo_list_new.exception;

public class TaskNotFoundException extends RuntimeException {
  public TaskNotFoundException(String message) {super(message);
  }
}
