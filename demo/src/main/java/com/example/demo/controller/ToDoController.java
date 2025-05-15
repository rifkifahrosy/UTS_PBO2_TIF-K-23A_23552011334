package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.ToDo;
import com.example.demo.model.User;
import com.example.demo.repository.SubTaskRepository;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private SubTaskRepository subTaskRepository;


    @Autowired
    private UserService userService;

    @GetMapping
    public String index(@RequestParam(name = "filter", defaultValue = "all") String filter,
                    Model model, Principal principal) {

    User user = userService.findByUsername(principal.getName());
    if (user == null) {
        throw new RuntimeException("User tidak ditemukan: " + principal.getName());
    }

    List<ToDo> todos = switch (filter) {
        case "completed" -> toDoRepository.findByUserAndCompletedTrue(user);
        case "not-completed" -> toDoRepository.findByUserAndCompletedFalse(user);
        default -> toDoRepository.findByUser(user);
    };

    todos = todos.stream()
        .peek(todo -> todo.setSubTasks(subTaskRepository.findByTodo(todo)))
        .collect(Collectors.toList());

    model.addAttribute("todos", todos);
    model.addAttribute("filter", filter);
    model.addAttribute("newTodo", new ToDo());
    model.addAttribute("principal", principal);
    return "todos";
}


    @PostMapping("/add")
    public String addTodo(@Valid @ModelAttribute ToDo todo, BindingResult result, Principal principal, Model model) {
    if (result.hasErrors()) {
        User user = userService.findByUsername(principal.getName());
        List<ToDo> todos = toDoRepository.findByUser(user);
        model.addAttribute("todos", todos);
        return "todos";
    }

    User user = userService.findByUsername(principal.getName());
    todo.setUser(user);
    toDoRepository.save(todo);
    return "redirect:/todos";
}


    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id, Principal principal) {
        Optional<ToDo> todo = toDoRepository.findById(id);
        todo.ifPresent(t -> {
            if (t.getUser().getUsername().equals(principal.getName())) {
                toDoRepository.deleteById(id);
            }
        });
        return "redirect:/todos";
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id, Principal principal) {
        Optional<ToDo> todo = toDoRepository.findById(id);
        todo.ifPresent(t -> {
            if (t.getUser().getUsername().equals(principal.getName())) {
                t.setCompleted(!t.isCompleted());
                toDoRepository.save(t);
            }
        });
        return "redirect:/todos";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("todo", new ToDo());
        return "create";
    }
    @PostMapping("/create")
    public String createTodo(@Valid @ModelAttribute ToDo todo, BindingResult result, Principal principal) {
    if (result.hasErrors()) {
        return "create";
    }

    User user = userService.findByUsername(principal.getName());
    todo.setUser(user);
    toDoRepository.save(todo);

    return "redirect:/todos";
    
}

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Principal principal, Model model) {
    Optional<ToDo> todo = toDoRepository.findById(id);
    if (todo.isEmpty() || !todo.get().getUser().getUsername().equals(principal.getName())) {
        return "redirect:/todos";
    }

    model.addAttribute("todo", todo.get());
    return "edit";
}

    @PostMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id,
                       @Valid @ModelAttribute ToDo todo,
                       BindingResult result,
                       Principal principal,
                       RedirectAttributes redirectAttributes) {

    if (result.hasErrors()) {
        return "edit";
    }

    Optional<ToDo> existingTodo = toDoRepository.findById(id);
    if (existingTodo.isPresent()) {
        ToDo t = existingTodo.get();
        if (!t.getUser().getUsername().equals(principal.getName())) {
            return "redirect:/todos";
        }

        t.setTitle(todo.getTitle());
        t.setCompleted(todo.isCompleted());
        t.setDeadline(todo.getDeadline());
        toDoRepository.save(t);

        redirectAttributes.addFlashAttribute("successMessage", "Tugas berhasil diperbarui!");
    }

    return "redirect:/todos";
}
    
}
