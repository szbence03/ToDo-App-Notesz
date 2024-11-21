package com.example.TodoApp.service;

import com.example.TodoApp.logic.TodoItem;
import com.example.TodoApp.logic.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoItem> getAllTodos() {
        return todoRepository.findAll();
    }

    public void addTodoItem(String description) {
        TodoItem newItem = new TodoItem(description);
        todoRepository.addItem(newItem);
    }

    public void deleteLastTodoItem() {
        todoRepository.deleteLastItem();
    }
}
