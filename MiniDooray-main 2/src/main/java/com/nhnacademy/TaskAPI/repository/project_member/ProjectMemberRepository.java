package com.nhnacademy.TaskAPI.repository.project_member;

import com.nhnacademy.TaskAPI.dto.project_member.ProjectMemberDto;
import com.nhnacademy.TaskAPI.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.PK>, ProjectMemberRepositoryCustom {
    ProjectMemberDto findProjectMemberByPk_ProjectIdAndPk_MemberId(Long projectId, Long memberId);
    void deleteProjectMemberByPk_ProjectIdAndPk_MemberId(Long projectId, Long memberId);
}
