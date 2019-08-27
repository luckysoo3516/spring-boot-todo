package com.puter.todo.controller;

import com.puter.todo.domain.Task;
import com.puter.todo.dto.TaskDto;
import com.puter.todo.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/tasks")
    public List<TaskDto> list() {
        var tasks = taskService.getTasks();

        var taskDtos = tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());

        return taskDtos;
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> create(
            @Valid @RequestBody TaskDto taskDto
    ) throws URISyntaxException {
        String title = taskDto.getTitle();
        String description = taskDto.getDescription();

        taskService.addTask(title, description);

        String url = "/tasks/1";
        return ResponseEntity.created(new URI(url)).body("");
    }

}
