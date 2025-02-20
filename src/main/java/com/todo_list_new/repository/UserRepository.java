package com.todo_list_new.repository;

import com.todo_list_new.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String username);

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
