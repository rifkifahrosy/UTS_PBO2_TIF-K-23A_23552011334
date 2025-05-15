package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ToDo;
import com.example.demo.model.User;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByCompletedTrue();
    List<ToDo> findByCompletedFalse();
    List<ToDo> findByUser(User user);
    List<ToDo> findByUserAndCompletedTrue(User user);
    List<ToDo> findByUserAndCompletedFalse(User user);

    
    
}
