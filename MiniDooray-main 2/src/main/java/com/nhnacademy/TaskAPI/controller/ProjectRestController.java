package com.nhnacademy.TaskAPI.controller;

import com.nhnacademy.TaskAPI.dto.project.ProjectDto;
import com.nhnacademy.TaskAPI.dto.project.ProjectResponse;
import com.nhnacademy.TaskAPI.dto.project.ProjectRequest;
import com.nhnacademy.TaskAPI.dto.project_member.ProjectMemberDto;
import com.nhnacademy.TaskAPI.service.project.ProjectService;
import com.nhnacademy.TaskAPI.service.project_member.ProjectMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectRestController {
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    public ProjectRestController(ProjectService projectService, ProjectMemberService projectMemberService) {
        this.projectService = projectService;
        this.projectMemberService = projectMemberService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }

    @GetMapping("/members")
    public ResponseEntity<List<ProjectMemberDto>> getMembers(@RequestParam("projectId") Long projectId) {
        return ResponseEntity.ok(projectMemberService.findAll(projectId));
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<ProjectMemberDto> getMember(@RequestParam("projectId") Long projectId, @PathVariable Long memberId) {
        return ResponseEntity.ok(projectMemberService.getProjectMember(projectId, memberId));
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest projectRequest) {
        return ResponseEntity.ok(projectService.createProject(projectRequest));
    }

    @PatchMapping("/update")
    public ResponseEntity<ProjectResponse> updateProject(@RequestParam("projectId") Long projectId, @RequestBody ProjectRequest projectRequest) {
        return ResponseEntity.ok(projectService.updateProject(projectId, projectRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProject(@RequestParam("projectId") Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }
}
