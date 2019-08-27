package com.puter.todo.controller;

import com.puter.todo.domain.Task;
import com.puter.todo.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void list() throws Exception {
        mvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[")));

        verify(taskService).getTasks();
    }

    @Test
    public void createWithValidData() throws Exception {
        mvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Umm first\",\"description\":\"this is first\"}"))
                .andExpect(status().isCreated());

        verify(taskService).addTask(eq("Umm first"), eq("this is first"));
    }

    @Test
    public void createWithInvalidData() throws Exception {
        mvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"\",\"description\":\"this is first\"}"))
                .andExpect(status().isBadRequest());

        verify(taskService, never()).addTask(any(), any());
    }

}
