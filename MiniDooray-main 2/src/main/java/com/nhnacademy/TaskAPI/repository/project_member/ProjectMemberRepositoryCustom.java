package com.nhnacademy.TaskAPI.repository.project_member;

import com.nhnacademy.TaskAPI.dto.project_member.ProjectMemberDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectMemberRepositoryCustom {
    List<ProjectMemberDto> findProjectMemberAll(Long projectId);
}
