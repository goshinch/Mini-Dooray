package com.nhnacademy.TaskAPI.service.project_member;

import com.nhnacademy.TaskAPI.dto.project_member.ProjectMemberDto;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMemberDto> findAll(Long projectId);

    ProjectMemberDto getProjectMember(Long projectId, Long memberId);

    ProjectMemberDto newProjectMember(Long projectId, Long memberId);

    String removeProjectMember(Long projectId, Long memberId);
}
