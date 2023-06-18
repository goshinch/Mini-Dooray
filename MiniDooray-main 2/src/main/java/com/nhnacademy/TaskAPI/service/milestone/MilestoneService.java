package com.nhnacademy.TaskAPI.service.milestone;

import com.nhnacademy.TaskAPI.dto.milestone.MilestoneDto;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneRequest;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneResponse;

import java.util.List;

public interface MilestoneService {
    List<MilestoneDto> getFindAll();
    List<MilestoneDto> getFindAllByProjectId(Long projectId);
    MilestoneDto getMilestone(Long id);
    MilestoneDto createMilestone(Long projectId,MilestoneRequest milestoneRequest);
    MilestoneResponse updateMilestone(Long milestoneId,Long projectId, MilestoneRequest milestoneRequest);
    String deleteMilestone(Long id);
}
