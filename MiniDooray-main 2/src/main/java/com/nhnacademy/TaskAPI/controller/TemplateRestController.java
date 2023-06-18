package com.nhnacademy.TaskAPI.controller;

import com.nhnacademy.TaskAPI.dto.template.TemplateRequest;
import com.nhnacademy.TaskAPI.dto.template.TemplateResponse;
import com.nhnacademy.TaskAPI.service.template.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateRestController {
    private final TemplateService templateService;

    public TemplateRestController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public ResponseEntity<List<TemplateResponse>> getTemplates(@RequestParam("projectId") Long projectId) {
        return ResponseEntity.ok(templateService.findAll(projectId));
    }

    @GetMapping("/{templateId}")
    public ResponseEntity<TemplateResponse> getTemplate(@RequestParam("projectId") Long projectId, @PathVariable Long templateId) {
        return ResponseEntity.ok(templateService.getTemplate(projectId, templateId));
    }

    @PostMapping("/new/{templateId}")
    public ResponseEntity<TemplateResponse> createTemplate(@RequestParam("projectId") Long projectId, @RequestBody TemplateRequest templateRequest) {
        return ResponseEntity.ok(templateService.createTemplate(projectId, templateRequest));
    }

    @PatchMapping("/update/{templateId}")
    public ResponseEntity<TemplateResponse> updateTemplate(@RequestParam("projectId") Long projectId, @PathVariable Long templateId, @RequestBody TemplateRequest templateRequest) {
        return ResponseEntity.ok(templateService.updateTemplate(projectId, templateId, templateRequest));
    }

    @DeleteMapping("/delete/{templateId}")
    public ResponseEntity<String> deleteTemplate(@RequestParam("projectId") Long projectId, @PathVariable Long templateId) {
        return ResponseEntity.ok(templateService.deleteTemplate(projectId, templateId));
    }
}
