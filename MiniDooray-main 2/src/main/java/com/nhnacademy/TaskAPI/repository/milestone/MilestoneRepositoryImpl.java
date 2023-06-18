package com.nhnacademy.TaskAPI.repository.milestone;

import com.nhnacademy.TaskAPI.dto.milestone.MilestoneDto;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneRequest;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneResponse;
import com.nhnacademy.TaskAPI.entity.Milestone;
import com.nhnacademy.TaskAPI.entity.QMilestone;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MilestoneRepositoryImpl extends QuerydslRepositorySupport implements MilestoneRepositoryCustom {
    public MilestoneRepositoryImpl() {
        super(Milestone.class);
    }

    @Override
    public List<MilestoneDto> findMilestoneAll() {
        QMilestone qMilestone = QMilestone.milestone;

        return from(qMilestone)
                .select(Projections.constructor(
                        MilestoneDto.class,
                        qMilestone.id,
                        qMilestone.project.projectId,
                        qMilestone.milestoneName,
                        qMilestone.startAt,
                        qMilestone.endAt
                ))
                .fetch();
    }

    @Override
    public List<MilestoneDto> findMilestoneAllByProjectID(Long projectId) {
        QMilestone qMilestone = QMilestone.milestone;

        return from(qMilestone)
                .select(Projections.constructor(
                        MilestoneDto.class,
                        qMilestone.id,
                        qMilestone.project.projectId,
                        qMilestone.milestoneName,
                        qMilestone.startAt,
                        qMilestone.endAt
                ))
                .where(qMilestone.project.projectId.eq(projectId))
                .fetch();

    }

    @Override
    public MilestoneDto findMilestoneById(Long id) {
        QMilestone qMilestone = QMilestone.milestone;

        return from(qMilestone)
                .select(Projections.constructor(
                        MilestoneDto.class,
                        qMilestone.id,
                        qMilestone.project.projectId,
                        qMilestone.milestoneName,
                        qMilestone.startAt,
                        qMilestone.endAt))
                .where(qMilestone.id.eq(id))
                .fetchFirst();
    }

    @Override
    public MilestoneResponse updateMilestoneById(Long id, MilestoneRequest milestoneRequest) {
        QMilestone qMilestone = QMilestone.milestone;

        update(qMilestone)
                .where(qMilestone.id.eq(id))
                .set(qMilestone.project.projectId, milestoneRequest.getProjectId())
                .set(qMilestone.milestoneName, milestoneRequest.getMilestoneName())
                .set(qMilestone.startAt, milestoneRequest.getStartAt())
                .set(qMilestone.endAt, milestoneRequest.getEndAt())
                .execute();

        return from(qMilestone)
                .select(Projections.bean(MilestoneResponse.class,
                        qMilestone.project.projectId,
                        qMilestone.milestoneName,
                        qMilestone.startAt,
                        qMilestone.endAt))
                .where(qMilestone.id.eq(id))
                .fetchFirst();
    }
}
