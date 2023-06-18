package com.nhnacademy.TaskAPI.dto.task;

import lombok.Data;

@Data
public class TaskRequest {
    private String taskName;
    private String description;
    private String state;
}
