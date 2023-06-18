package com.nhnacademy.TaskAPI.repository.milestone;

import com.nhnacademy.TaskAPI.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>, MilestoneRepositoryCustom{
}
