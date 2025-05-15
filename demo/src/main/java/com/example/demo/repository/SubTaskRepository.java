package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SubTask;
import com.example.demo.model.ToDo;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
    List<SubTask> findByTodo(ToDo todo);
}

