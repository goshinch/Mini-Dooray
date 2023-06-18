package com.nhnacademy.TaskAPI.controller;

import com.nhnacademy.TaskAPI.dto.milestone.MilestoneDto;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneRequest;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneResponse;
import com.nhnacademy.TaskAPI.service.milestone.MilestoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/milestones")
public class MilestoneRestController {
    private final MilestoneService milestoneService;

    public MilestoneRestController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<MilestoneDto>> getMilestoneAll() {
        return ResponseEntity.ok(milestoneService.getFindAll());
    }

    @GetMapping("/list/{projectId}")
    public ResponseEntity<List<MilestoneDto>> getMilestoneList(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(milestoneService.getFindAllByProjectId(projectId));
    }

    @GetMapping("/{milestoneId}")
    public MilestoneDto getMilestone(@PathVariable("milestoneId")Long milestoneId) {
        return milestoneService.getMilestone(milestoneId);
    }

    @PostMapping("/new")
    public MilestoneDto createMilestone(@RequestParam("projectId") Long projectId,
            @RequestBody MilestoneRequest milestoneRequest) {
        return milestoneService.createMilestone(projectId, milestoneRequest);
    }

    @PatchMapping("/update/{milestoneId}")
    public MilestoneResponse updateMilestone(@PathVariable("milestoneId")Long milestoneId,
                                             @RequestParam("projectId") Long projectId,
                                             @RequestBody MilestoneRequest milestoneRequest) {
        return milestoneService.updateMilestone(milestoneId, projectId, milestoneRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMilestone(@RequestParam("miletstoneId") Long miletstoneId) {
        return ResponseEntity.ok(milestoneService.deleteMilestone(miletstoneId));
    }
}
