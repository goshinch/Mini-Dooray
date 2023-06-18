package com.nhnacademy.TaskAPI.dto.project;

import com.nhnacademy.TaskAPI.entity.Project;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProjectResponse {
    private Long projectId;
    private Long masterId;
    private String name;
    private String explanation;
    private Project.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
