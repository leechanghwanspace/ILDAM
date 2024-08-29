package com.dailylog.termproject.repository;

import com.dailylog.termproject.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
