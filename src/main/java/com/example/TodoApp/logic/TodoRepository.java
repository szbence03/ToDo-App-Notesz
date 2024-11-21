package com.example.TodoApp.logic;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoRepository {
    private final List<TodoItem> todoList = new ArrayList<>();

    public List<TodoItem> findAll() {
        return new ArrayList<>(todoList);
    }

    public void addItem(TodoItem item) {
        todoList.add(item);
    }

    public void deleteLastItem() {
        if (!todoList.isEmpty()) {
            todoList.remove(todoList.size() - 1);
        }
    }
}
