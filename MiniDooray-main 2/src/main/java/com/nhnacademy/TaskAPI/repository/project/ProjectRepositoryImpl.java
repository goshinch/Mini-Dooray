package com.nhnacademy.TaskAPI.repository.project;

import com.nhnacademy.TaskAPI.dto.project.ProjectResponse;
import com.nhnacademy.TaskAPI.entity.*;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectRepositoryImpl extends QuerydslRepositorySupport implements ProjectRepositoryCustom {
    public ProjectRepositoryImpl() {
        super(Project.class);
    }

    @Override
    public List<ProjectResponse> findProjectAllBy() {
        QProject qProject = QProject.project;
        return from(qProject)
                .select(Projections.bean(
                        ProjectResponse.class,
                        qProject.projectId,
                        qProject.masterId,
                        qProject.name,
                        qProject.explanation,
                        qProject.status,
                        qProject.createdAt,
                        qProject.updatedAt
                )).fetch();
    }

    @Override
    public ProjectResponse updateProject(Project project) {
        QProject qProject = QProject.project;
        update(qProject)
                .set(qProject.name, project.getName())
                .set(qProject.explanation, project.getExplanation())
                .set(qProject.status, project.getStatus())
                .set(qProject.updatedAt, project.getUpdatedAt())
                .where(qProject.projectId.eq(project.getProjectId()))
                .execute();

        return findProjectByProjectId(project.getProjectId());
    }

    @Override
    public ProjectResponse findProjectByProjectId(Long id) {
        QProject qProject = QProject.project;
        return from(qProject)
                .select(Projections.bean(ProjectResponse.class,
                        qProject.projectId,
                        qProject.masterId,
                        qProject.name,
                        qProject.explanation,
                        qProject.status,
                        qProject.createdAt,
                        qProject.updatedAt
                ))
                .where(qProject.projectId.eq(id))
                .fetchFirst();
    }
}
