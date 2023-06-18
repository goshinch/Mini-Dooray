package com.nhnacademy.TaskAPI.controller;

import com.nhnacademy.TaskAPI.dto.milestone.MilestoneDto;
import com.nhnacademy.TaskAPI.dto.milestone.MilestoneRequest;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailDto;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailRequest;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailResponse;
import com.nhnacademy.TaskAPI.service.tagDetail.TagDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tagDetails")
public class TagDetailRestController {
    private final TagDetailService tagDetailService;

    public TagDetailRestController(TagDetailService tagDetailService) {
        this.tagDetailService = tagDetailService;
    }

    @GetMapping("/list/{projectId}")
    public ResponseEntity<List<TagDetailDto>> getTagDetailList(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(tagDetailService.getAllByProjectId(projectId));
    }

    @GetMapping("/{tagDetailId}")
    public TagDetailDto getTagDetail(@PathVariable("tagDetailId")Long tagDetailId) {
        return tagDetailService.getTagDetailById(tagDetailId);
    }

    @PostMapping("/new")
    public TagDetailDto createTagDetail(@RequestParam("projectId") Long projectId,
                                             @RequestBody TagDetailRequest tagDetailRequest) {
        return tagDetailService.createTagDetail(projectId, tagDetailRequest);
    }

    @PatchMapping("/update")
    private TagDetailResponse updateTagDetail(@RequestParam("tagDetailId") Long tagDetailId, @RequestBody TagDetailRequest tagDetailRequest) {
        return tagDetailService.updateTagDetailById(tagDetailId, tagDetailRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTagDetail(@RequestParam("tagDetailId") Long tagDetailId) {
        return ResponseEntity.ok(tagDetailService.deleteTagDetail(tagDetailId));
    }
}
