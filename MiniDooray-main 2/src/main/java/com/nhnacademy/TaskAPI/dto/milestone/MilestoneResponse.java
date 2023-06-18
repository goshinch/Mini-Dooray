package com.nhnacademy.TaskAPI.dto.milestone;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MilestoneResponse {
    private Long projectId;
    private String milestoneName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
