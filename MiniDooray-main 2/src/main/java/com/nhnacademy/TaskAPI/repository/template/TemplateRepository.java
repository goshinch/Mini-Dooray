package com.nhnacademy.TaskAPI.repository.template;

import com.nhnacademy.TaskAPI.dto.template.TemplateResponse;
import com.nhnacademy.TaskAPI.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Template.PK>, TemplateRepositoryCustom {
    List<TemplateResponse> findTemplateByPk_ProjectId(Long projectId);
}
