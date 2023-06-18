package com.nhnacademy.TaskAPI.repository.milestone;

import com.nhnacademy.TaskAPI.dto.milestone.MilestoneDto;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneRequest;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneResponse;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MilestoneRepositoryCustom {
    List<MilestoneDto> findMilestoneAll();
    List<MilestoneDto> findMilestoneAllByProjectID(Long projectId);
    MilestoneDto findMilestoneById(Long id);
    MilestoneResponse updateMilestoneById(Long id, MilestoneRequest milestoneRequest);
}
