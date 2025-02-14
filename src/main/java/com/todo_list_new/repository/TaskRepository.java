package com.todo_list_new.repository;

import com.todo_list_new.model.Task;
import com.todo_list_new.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUser(Users user);
}
