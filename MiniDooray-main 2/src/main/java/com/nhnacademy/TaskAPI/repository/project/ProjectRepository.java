package com.nhnacademy.TaskAPI.repository.project;

import com.nhnacademy.TaskAPI.dto.project.ProjectDto;
import com.nhnacademy.TaskAPI.dto.project.ProjectResponse;
import com.nhnacademy.TaskAPI.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {

}
