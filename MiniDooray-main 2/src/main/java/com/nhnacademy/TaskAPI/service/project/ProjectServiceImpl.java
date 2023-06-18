package com.nhnacademy.TaskAPI.service.project;

import com.nhnacademy.TaskAPI.dto.project.ProjectDto;
import com.nhnacademy.TaskAPI.dto.project.ProjectResponse;
import com.nhnacademy.TaskAPI.dto.project.ProjectRequest;
import com.nhnacademy.TaskAPI.entity.Project;
import com.nhnacademy.TaskAPI.exception.CreationFailedException;
import com.nhnacademy.TaskAPI.exception.NotFoundException;
import com.nhnacademy.TaskAPI.repository.project.ProjectRepository;
import com.nhnacademy.TaskAPI.repository.template.TemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    private static final String PROJECT_NOT_FOUND_MESSAGE = "Project not found ID: ";
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectResponse> findAll() {
        List<ProjectResponse> projectDtoList = projectRepository.findProjectAllBy();
        if (projectDtoList.isEmpty()) throw new NotFoundException("No projects found");
        return projectDtoList;
    }

    @Override
    public ProjectResponse getProject(Long id) {
        ProjectResponse projectResponse = projectRepository.findProjectByProjectId(id);
        if (projectResponse == null) throw new NotFoundException(PROJECT_NOT_FOUND_MESSAGE + id);
        return projectResponse;
    }

    @Transactional
    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        Project project = new Project();
        project.setName(projectRequest.getName());
        project.setExplanation(projectRequest.getExplanation());
        project.setStatus(Project.Status.valueOf(projectRequest.getStatus()));
        project.setUpdatedAt(LocalDateTime.now());
        try {
            return projectRepository.findProjectByProjectId(projectRepository.save(project).getProjectId());
        } catch (PersistenceException ex) { // 영속성 작업중에 일어날 수 있는 데이터베이스 access, 데이터 중복 등을 catch
            throw new CreationFailedException("Project creation failed err: " + ex);
        }
    }

    @Transactional
    @Override
    public ProjectResponse updateProject(Long projectId, ProjectRequest projectRequest) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException(PROJECT_NOT_FOUND_MESSAGE + projectId));
        project.setName(projectRequest.getName());
        project.setExplanation(projectRequest.getExplanation());
        project.setStatus(Project.Status.valueOf(projectRequest.getStatus()));
        return projectRepository.updateProject(project);
    }

    @Transactional
    @Override
    public String deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException(PROJECT_NOT_FOUND_MESSAGE + id));
        projectRepository.delete(project);
        return "{\"result\":\"OK\"}";
    }
}
