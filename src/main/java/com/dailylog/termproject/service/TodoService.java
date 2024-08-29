package com.dailylog.termproject.service;

import com.dailylog.termproject.entity.Todo;
import com.dailylog.termproject.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public void addTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    public void completeTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setCompleted(true);
        todoRepository.save(todo);
    }

    public void updateTodoDescription(Long id, String description) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setDescription(description);
        todoRepository.save(todo);
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow();
    }
}
