package com.todo_list_new.repository;

import com.todo_list_new.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByName(String username);

    boolean existsByName(String name);
}
