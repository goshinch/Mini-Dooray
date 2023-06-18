package com.nhnacademy.TaskAPI.dto.project;

import lombok.Data;

@Data
public class ProjectRequest {
    private String name;
    private String masterId;
    private String explanation;
    private String status;
}
