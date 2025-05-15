package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.SubTask;
import com.example.demo.model.ToDo;
import com.example.demo.repository.SubTaskRepository;
import com.example.demo.repository.ToDoRepository;

@Controller
@RequestMapping("/subtasks")
public class SubTaskController {

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Autowired
    private ToDoRepository toDoRepository;

    @PostMapping("/add")
    public String addSubtask(@RequestParam Long todoId,
                             @RequestParam String title,
                             RedirectAttributes redirectAttributes) {
        Optional<ToDo> todoOpt = toDoRepository.findById(todoId);
        if (todoOpt.isPresent()) {
            SubTask subtask = new SubTask();
            subtask.setTitle(title.trim());
            subtask.setCompleted(false);
            subtask.setTodo(todoOpt.get());
            subTaskRepository.save(subtask);
            redirectAttributes.addFlashAttribute("successMessage", "Subtugas berhasil ditambahkan!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Todo tidak ditemukan.");
        }
        return "redirect:/todos/edit/" + todoId;
    }

    @GetMapping("/toggle/{id}")
    public String toggleSubTask(@PathVariable Long id) {
        SubTask subTask = subTaskRepository.findById(id).orElseThrow();
        subTask.setCompleted(!subTask.isCompleted());
        subTaskRepository.save(subTask);
        return "redirect:/todos/edit/" + subTask.getTodo().getId();
    }
    @GetMapping("/delete/{id}")
    public String deleteSubTask(@PathVariable Long id) {
        SubTask subTask = subTaskRepository.findById(id).orElseThrow();
        Long todoId = subTask.getTodo().getId();
        subTaskRepository.delete(subTask);
        return "redirect:/todos/edit/" + todoId;
    }
}
