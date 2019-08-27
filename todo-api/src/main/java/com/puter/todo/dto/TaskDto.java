package com.puter.todo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class TaskDto {

    private Long id;

    @NotEmpty
    private String title;

    private String description;

    private boolean checked;
}
