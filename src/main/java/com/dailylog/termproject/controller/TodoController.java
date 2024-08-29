package com.dailylog.termproject.controller;

import com.dailylog.termproject.entity.Todo;
import com.dailylog.termproject.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public String getTodos(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "plan";  // plan.html 파일을 반환
    }

    @PostMapping
    public String addTodo(@RequestParam String description) {
        Todo todo = new Todo();
        todo.setDescription(description);
        todo.setCompleted(false);
        todoService.addTodo(todo);
        return "redirect:/todos";  // 추가 후 /todos 경로로 리디렉션
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";  // 삭제 후 /todos 경로로 리디렉션
    }

    @GetMapping("/complete/{id}")
    public String completeTodo(@PathVariable Long id) {
        todoService.completeTodoById(id);
        return "redirect:/todos";  // 완료 후 /todos 경로로 리디렉션
    }

    @GetMapping("/edit/{id}")
    public String editTodoForm(@PathVariable Long id, Model model) {
        model.addAttribute("todo", todoService.getTodoById(id));
        return "editTodo";  // editTodo.html 파일을 반환
    }

    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable Long id, @RequestParam String description) {
        todoService.updateTodoDescription(id, description);
        return "redirect:/todos";  // 수정 후 /todos 경로로 리디렉션
    }
}
