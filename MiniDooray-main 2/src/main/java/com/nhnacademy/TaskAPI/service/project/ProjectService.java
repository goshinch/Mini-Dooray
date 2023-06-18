package com.nhnacademy.TaskAPI.service.project;

import com.nhnacademy.TaskAPI.dto.project.ProjectDto;
import com.nhnacademy.TaskAPI.dto.project.ProjectResponse;
import com.nhnacademy.TaskAPI.dto.project.ProjectRequest;

import java.util.List;

public interface ProjectService {
    List<ProjectResponse> findAll();
    ProjectResponse getProject(Long id);
    ProjectResponse createProject(ProjectRequest project);
    ProjectResponse updateProject(Long id, ProjectRequest projectRequest);
    String deleteProject(Long id);
}
