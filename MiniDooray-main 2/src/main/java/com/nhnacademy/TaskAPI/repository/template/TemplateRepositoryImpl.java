package com.nhnacademy.TaskAPI.repository.template;

import com.nhnacademy.TaskAPI.dto.template.TemplateRequest;
import com.nhnacademy.TaskAPI.dto.template.TemplateResponse;
import com.nhnacademy.TaskAPI.entity.QProject;
import com.nhnacademy.TaskAPI.entity.QTemplate;
import com.nhnacademy.TaskAPI.entity.Template;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class TemplateRepositoryImpl extends QuerydslRepositorySupport implements TemplateRepositoryCustom {
    public TemplateRepositoryImpl() {
        super(Template.class);
    }

    @Override
    public TemplateResponse updateTemplate(Long projectId, Long templateId, TemplateRequest templateRequest) {
        QTemplate qTemplate = QTemplate.template;
        QProject qProject = QProject.project;

        update(qTemplate)
                .set(qTemplate.pk.templateId, templateId)
                .set(qTemplate.project, qProject)
                .set(qTemplate.pk.projectId, projectId)
                .set(qTemplate.name, templateRequest.getName())
                .set(qTemplate.explanation, templateRequest.getExplanation())
                .where(qTemplate.pk.projectId.eq(projectId)
                        .and(qTemplate.pk.templateId.eq(templateId))
                        .and(qTemplate.project.eq(qProject)))
                .execute();

        return findTemplate(projectId, templateId);
    }

    @Override
    public TemplateResponse findTemplate(Long projectId, Long templateId) {
        QTemplate qTemplate = QTemplate.template;

        return from(qTemplate)
                .select(Projections.bean(
                        TemplateResponse.class,
                        qTemplate.pk.templateId,
                        qTemplate.name,
                        qTemplate.explanation
                ))
                .where(qTemplate.pk.projectId.eq(projectId)
                        .and(qTemplate.pk.templateId.eq(templateId)))
                .fetchFirst();
    }

    @Override
    public String deleteTemplate(Long projectId, Long templateId) {
        QTemplate qTemplate = QTemplate.template;
        delete(qTemplate)
                .where(qTemplate.pk.projectId.eq(projectId)
                        .and(qTemplate.pk.templateId.eq(templateId)))
                .execute();
        return null;
    }
}
