package com.nhnacademy.TaskAPI.service.project_member;

import com.nhnacademy.TaskAPI.dto.project_member.ProjectMemberDto;
import com.nhnacademy.TaskAPI.entity.ProjectMember;
import com.nhnacademy.TaskAPI.repository.project_member.ProjectMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;

    public ProjectMemberServiceImpl(ProjectMemberRepository projectMemberRepository) {
        this.projectMemberRepository = projectMemberRepository;
    }
    @Override
    public List<ProjectMemberDto> findAll(Long projectId) {
        return projectMemberRepository.findProjectMemberAll(projectId);
    }

    @Override
    public ProjectMemberDto getProjectMember(Long projectId, Long memberId) {
        return projectMemberRepository.findProjectMemberByPk_ProjectIdAndPk_MemberId(projectId, memberId);
    }

    @Override
    public ProjectMemberDto newProjectMember(Long projectId, Long memberId) {
        ProjectMember projectMember = new ProjectMember();
        ProjectMember.PK pk = new ProjectMember.PK();
        pk.setMemberId(memberId);
        projectMember.setPk(pk);
        ProjectMember projectMember1 = projectMemberRepository.save(projectMember);
        return getProjectMember(projectMember1.getPk().getProjectId(), projectMember1.getPk().getMemberId());
    }

    @Override
    public String removeProjectMember(Long projectId, Long memberId) {
        projectMemberRepository.deleteProjectMemberByPk_ProjectIdAndPk_MemberId(projectId, memberId);
        return "{\"result\":\"OK\"}";
    }

}
