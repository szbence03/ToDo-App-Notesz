package com.example.TodoApp;

import com.example.TodoApp.logic.TodoItem;
import com.example.TodoApp.logic.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoRepositoryTest {

    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository = new TodoRepository();
    }

    @Test
    void findAll_returnsAllItems() {
        TodoItem item1 = new TodoItem("Item 1");
        TodoItem item2 = new TodoItem("Item 2");
        todoRepository.addItem(item1);
        todoRepository.addItem(item2);

        List<TodoItem> result = todoRepository.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(item1));
        assertTrue(result.contains(item2));
    }

    @Test
    void findAll_returnsEmptyListWhenNoItems() {
        List<TodoItem> result = todoRepository.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void addItem_addsNewItemToList() {
        TodoItem item = new TodoItem("New Item");

        todoRepository.addItem(item);

        List<TodoItem> result = todoRepository.findAll();
        assertEquals(1, result.size());
        assertTrue(result.contains(item));
    }

    @Test
    void deleteLastItem_removesLastItemFromList() {
        TodoItem item1 = new TodoItem("Item 1");
        TodoItem item2 = new TodoItem("Item 2");
        todoRepository.addItem(item1);
        todoRepository.addItem(item2);

        todoRepository.deleteLastItem();

        List<TodoItem> result = todoRepository.findAll();
        assertEquals(1, result.size());
        assertTrue(result.contains(item1));
        assertFalse(result.contains(item2));
    }

    @Test
    void deleteLastItem_doesNothingWhenListIsEmpty() {
        todoRepository.deleteLastItem();

        List<TodoItem> result = todoRepository.findAll();
        assertTrue(result.isEmpty());
    }
}