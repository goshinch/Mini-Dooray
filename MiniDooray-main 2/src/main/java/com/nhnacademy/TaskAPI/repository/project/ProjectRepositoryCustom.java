package com.nhnacademy.TaskAPI.repository.project;

import com.nhnacademy.TaskAPI.dto.project.ProjectDto;
import com.nhnacademy.TaskAPI.dto.project.ProjectResponse;
import com.nhnacademy.TaskAPI.entity.Project;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectRepositoryCustom {
    List<ProjectResponse> findProjectAllBy();
    ProjectResponse updateProject(Project project);
    ProjectResponse findProjectByProjectId(Long id);
}
