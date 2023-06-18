package com.nhnacademy.TaskAPI.dto.task;

import com.nhnacademy.TaskAPI.entity.Task;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskDto {
    private Long taskId;
    private Long projectId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String taskName;
    private String description;
    private Task.State state;
}