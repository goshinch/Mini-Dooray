package com.nhnacademy.TaskAPI.dto.tagDetail;

import lombok.Data;

@Data
public class TagDetailRequest {
    private Long projectId;
    private String tagName;
}
