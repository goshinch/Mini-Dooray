package com.nhnacademy.TaskAPI.dto.template;

import lombok.Data;

@Data
public class TemplateResponse {
    private Long templateId;
    private String name;
    private String explanation;
}
