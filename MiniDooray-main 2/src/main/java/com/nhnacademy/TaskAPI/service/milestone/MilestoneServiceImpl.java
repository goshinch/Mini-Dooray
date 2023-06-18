package com.nhnacademy.TaskAPI.service.milestone;

import com.nhnacademy.TaskAPI.dto.milestone.MilestoneDto;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneRequest;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneResponse;
import com.nhnacademy.TaskAPI.entity.Milestone;
import com.nhnacademy.TaskAPI.exception.NotFoundException;
import com.nhnacademy.TaskAPI.repository.milestone.MilestoneRepository;
import com.nhnacademy.TaskAPI.repository.project.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("milestoneService")
public class MilestoneServiceImpl implements MilestoneService{

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;
    private static final String TASK_NOT_FOUND_MESSAGE = "Task not found ID: ";

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository, ProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<MilestoneDto> getFindAll() {
        List<MilestoneDto> milestoneDtoList = milestoneRepository.findMilestoneAll();
        if (milestoneDtoList.isEmpty()) {
            throw new NotFoundException("Milestones not found");
        }
        return milestoneDtoList;
    }

    @Override
    public List<MilestoneDto> getFindAllByProjectId(Long projectId) {
        List<MilestoneDto> milestoneDtoList = milestoneRepository.findMilestoneAllByProjectID(projectId);
        if (milestoneDtoList.isEmpty()) {
            throw new NotFoundException("Milestones not found");
        }
        return milestoneDtoList;
    }

    @Override
    public MilestoneDto getMilestone(Long id) {
        MilestoneDto milestoneDto = milestoneRepository.findMilestoneById(id);
        if (milestoneDto == null) {
            throw new NotFoundException("Milestones not found : " + id);
        }
        return milestoneDto;
    }

    @Override
    public MilestoneDto createMilestone(Long projectId,MilestoneRequest milestoneRequest) {
        Milestone milestone = new Milestone();
        milestone.setProject(projectRepository.findById(milestoneRequest.getProjectId()).orElseThrow(() -> new NotFoundException(TASK_NOT_FOUND_MESSAGE + milestoneRequest.getProjectId())));
        milestone.setMilestoneName(milestoneRequest.getMilestoneName());
        milestone.setStartAt(milestoneRequest.getStartAt());
        milestone.setEndAt(milestoneRequest.getEndAt());
        return milestoneRepository.findMilestoneById(milestoneRepository.save(milestone).getId());
    }

    @Override
    public MilestoneResponse updateMilestone(Long milestoneId,Long projectId, MilestoneRequest milestoneRequest) {
        MilestoneDto milestoneDto = milestoneRepository.findMilestoneById(milestoneId);
        if(milestoneDto == null) {
            throw new NotFoundException("Milestones not found : " + milestoneId);
        }
        return milestoneRepository.updateMilestoneById(milestoneId, milestoneRequest);
    }

    @Override
    public String deleteMilestone(Long id) {
        milestoneRepository.deleteById(id);
        return "{\"result : ok\"}";
    }
}
