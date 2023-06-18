package com.nhnacademy.TaskAPI.service.tagDetail;

import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailDto;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailRequest;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailResponse;

import java.util.List;

public interface TagDetailService {
    List<TagDetailDto> getAllByProjectId(Long projectId);
    TagDetailDto getTagDetailById(Long id);
    TagDetailDto createTagDetail(Long projectId, TagDetailRequest tagDetailRequest);
    TagDetailResponse updateTagDetailById(Long tagDetailId, TagDetailRequest tagDetailRequest);
    String deleteTagDetail(Long id);
}
