package com.nhnacademy.TaskAPI.dto.task;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long taskId;
    private String projectName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
