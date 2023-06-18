package com.nhnacademy.TaskAPI.dto.milestone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MilestoneDto {
    private Long milestoneId;
    private Long projectId;
    private String milestoneName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
