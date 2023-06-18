package com.nhnacademy.TaskAPI.service.template;

import com.nhnacademy.TaskAPI.dto.template.TemplateRequest;
import com.nhnacademy.TaskAPI.dto.template.TemplateResponse;
import com.nhnacademy.TaskAPI.entity.Template;
import com.nhnacademy.TaskAPI.exception.CreationFailedException;
import com.nhnacademy.TaskAPI.exception.NotFoundException;
import com.nhnacademy.TaskAPI.repository.project.ProjectRepository;
import com.nhnacademy.TaskAPI.repository.template.TemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Transactional
@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
    private static final String TEMPLATE_NOT_FOUND_MESSAGE = "Template not found ID: ";
    private final TemplateRepository templateRepository;
    private final ProjectRepository projectRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository, ProjectRepository projectRepository) {
        this.templateRepository = templateRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<TemplateResponse> findAll(Long projectId) {
        return templateRepository.findTemplateByPk_ProjectId(projectId);
    }

    @Override
    public TemplateResponse getTemplate(Long projectId, Long templateId) {
        TemplateResponse templateDetailResponse = templateRepository.findTemplate(projectId, templateId);
        if (templateDetailResponse == null) throw new NotFoundException(TEMPLATE_NOT_FOUND_MESSAGE + "");
        return templateDetailResponse;
    }

    @Override
    public TemplateResponse createTemplate(Long projectId, TemplateRequest templateRequest) {
        try {
            Template.PK pk = new Template.PK();
            pk.setProjectId(projectId);
            Template template = new Template();
            template.setPk(pk);
            template.setProject(projectRepository.findById(projectId).orElse(null));
            template.setName(templateRequest.getName());
            template.setExplanation(templateRequest.getExplanation());
            return returnResponse(templateRepository.save(template));
        } catch (PersistenceException ex) { // 영속성 작업중에 일어날 수 있는 데이터베이스 access, 데이터 중복 등을 catch
            throw new CreationFailedException("Template creation failed err: " + ex);
        }
    }

    @Override
    public TemplateResponse updateTemplate(Long projectId, Long templateId, TemplateRequest templateRequest) {
        return templateRepository.updateTemplate(projectId, templateId, templateRequest);
    }

    @Override
    public String deleteTemplate(Long projectId, Long templateId) {
        getTemplate(projectId, templateId);
        templateRepository.deleteTemplate(projectId, templateId);
        return "{\"result\":\"OK\"}";
    }

    private TemplateResponse returnResponse(Template template) {
        TemplateResponse templateResponse = new TemplateResponse();
        templateResponse.setTemplateId(template.getPk().getTemplateId());
        templateResponse.setName(template.getName());
        templateResponse.setExplanation(template.getExplanation());
        return templateResponse;
    }
}
