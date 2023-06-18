package com.nhnacademy.TaskAPI.service.template;

import com.nhnacademy.TaskAPI.dto.template.TemplateRequest;
import com.nhnacademy.TaskAPI.dto.template.TemplateResponse;

import java.util.List;

public interface TemplateService {
    List<TemplateResponse> findAll(Long projectId);

    TemplateResponse getTemplate(Long projectId, Long templateId);

    TemplateResponse createTemplate(Long projectId, TemplateRequest templateRequest);

    TemplateResponse updateTemplate(Long projectId, Long templateId, TemplateRequest templateRequest);
    String deleteTemplate(Long projectId, Long templateDetailId);
}
