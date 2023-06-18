package com.nhnacademy.TaskAPI.repository.tagDetail;

import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailDto;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailRequest;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailResponse;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TagDetailRepositoryCustom {
    List<TagDetailDto> findTagDetailsId(Long projectId);
    TagDetailDto findTagDetailId(Long tagDetailId);
    TagDetailResponse updateTagDetail(Long tagDetailId, TagDetailRequest tagDetailRequest);
}
