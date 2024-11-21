package com.example.TodoApp.controller;

import com.example.TodoApp.logic.TodoItem;
import com.example.TodoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoItem> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public void addTodoItem(@RequestBody String description) {
        todoService.addTodoItem(description);
    }

    @DeleteMapping
    public void deleteLastTodoItem() {
        todoService.deleteLastTodoItem();
    }
}
