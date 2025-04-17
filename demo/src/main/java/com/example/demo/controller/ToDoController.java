package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDoRepository;

@Controller
@RequestMapping("/")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    
    @GetMapping
    public String index(@RequestParam(name = "filter", defaultValue = "all") String filter, Model model) {
        List<ToDo> todos;

        todos = switch (filter) {
            case "completed" -> toDoRepository.findByCompletedTrue();
            case "not-completed" -> toDoRepository.findByCompletedFalse();
            default -> toDoRepository.findAll();
        };

        model.addAttribute("todos", todos);
        model.addAttribute("newTodo", new ToDo()); // untuk form tambah
        return "index";
    }

    
    @PostMapping("/add")
    public String addTodo(@ModelAttribute ToDo todo) {
        toDoRepository.save(todo);
        return "redirect:/";
    }

    // POST: Menghapus todo
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        toDoRepository.deleteById(id);
        return "redirect:/";
    }

    // POST: Update status selesai
    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id) {
        Optional<ToDo> todo = toDoRepository.findById(id);
        todo.ifPresent(t -> {
            t.setCompleted(!t.isCompleted());
            toDoRepository.save(t);
        });
        return "redirect:/";
    }
}
