package com.nhnacademy.TaskAPI.repository.template;

import com.nhnacademy.TaskAPI.dto.template.TemplateRequest;
import com.nhnacademy.TaskAPI.dto.template.TemplateResponse;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TemplateRepositoryCustom {
    TemplateResponse updateTemplate(Long projectId, Long templateId, TemplateRequest templateRequest);
    TemplateResponse findTemplate(Long projectId, Long templateId);
    String deleteTemplate(Long projectId, Long templateDetailId);
}
