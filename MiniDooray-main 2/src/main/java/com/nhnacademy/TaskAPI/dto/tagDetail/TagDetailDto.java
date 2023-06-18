package com.nhnacademy.TaskAPI.dto.tagDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagDetailDto {
    private Long tagDetailId;
    private Long projectId;
    private String tagName;
}

