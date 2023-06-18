package com.nhnacademy.TaskAPI.dto.project_member;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class ProjectMemberDto {
    @Value("#{target.projectId}")
    private Long projectId;
    @Value("#{target.memberId}")
    private Long memberId;
}
