package com.nhnacademy.TaskAPI.repository.project_member;

import com.nhnacademy.TaskAPI.dto.project_member.ProjectMemberDto;
import com.nhnacademy.TaskAPI.entity.ProjectMember;
import com.nhnacademy.TaskAPI.entity.QProjectMember;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectMemberRepositoryImpl extends QuerydslRepositorySupport implements ProjectMemberRepositoryCustom {
    public ProjectMemberRepositoryImpl() {
        super(ProjectMember.class);
    }

    @Override
    public List<ProjectMemberDto> findProjectMemberAll(Long projectId) {
        QProjectMember qProjectMember = QProjectMember.projectMember;
        return from(qProjectMember)
                .select(Projections.bean(
                        ProjectMemberDto.class,
                        qProjectMember.pk.projectId,
                        qProjectMember.pk.memberId
                ))
                .where(qProjectMember.pk.projectId.eq(projectId))
                .fetch();
    }
}
