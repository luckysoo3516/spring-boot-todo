package com.puter.todo.service;

import com.puter.todo.domain.Task;
import com.puter.todo.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class TaskServiceTest {

    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        taskService = new TaskService(taskRepository);
    }

    @Test
    public void getTasks() {
        List<Task> mockTasks = new ArrayList<>();
        mockTasks.add(new Task());

        given(taskRepository.findAll()).willReturn(mockTasks);

        List<Task> tasks = taskService.getTasks();

        assertThat(tasks.size(), is(1));
    }

    @Test
    public void addTask() {
        Task task = new Task();

        taskService.addTask("Title", "Description");

        verify(taskRepository).save(any(Task.class));
    }
}
