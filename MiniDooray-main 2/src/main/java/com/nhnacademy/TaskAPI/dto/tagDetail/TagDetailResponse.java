package com.nhnacademy.TaskAPI.dto.tagDetail;

import lombok.Data;

@Data
public class TagDetailResponse {
    private Long tagDetailId;
    private Long projectId;
    private String tagName;

    public TagDetailResponse(Long projectId, String tagName) {
        this.projectId = projectId;
        this.tagName = tagName;
    }
}
