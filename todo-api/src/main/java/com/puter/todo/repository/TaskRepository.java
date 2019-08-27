package com.puter.todo.repository;

import com.puter.todo.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAll();

    Task save(Task task);
}
