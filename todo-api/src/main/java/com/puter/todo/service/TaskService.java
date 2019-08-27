package com.puter.todo.service;

import com.puter.todo.domain.Task;
import com.puter.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks;
    }

    public Task addTask(String title, String description) {
        Task task = Task.builder().title(title).description(description).build();
        return taskRepository.save(task);
    }
}
