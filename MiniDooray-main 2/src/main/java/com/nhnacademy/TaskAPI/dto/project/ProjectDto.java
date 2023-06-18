package com.nhnacademy.TaskAPI.dto.project;

import com.nhnacademy.TaskAPI.entity.Project;

import java.time.LocalDateTime;

public interface ProjectDto {
    Long getProjectId();
    String getName();
    Long getMasterId();
    String getExplanation();
    Project.Status getStatus();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}