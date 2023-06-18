package com.nhnacademy.TaskAPI.service.tagDetail;

import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailDto;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailRequest;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailResponse;
import com.nhnacademy.TaskAPI.entity.TagDetail;
import com.nhnacademy.TaskAPI.exception.NotFoundException;
import com.nhnacademy.TaskAPI.repository.project.ProjectRepository;
import com.nhnacademy.TaskAPI.repository.tagDetail.TagDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("tagDetailService")
public class TagDetailServiceImpl implements TagDetailService{

    private final TagDetailRepository tagDetailRepository;
    private final ProjectRepository projectRepository;

    public TagDetailServiceImpl(TagDetailRepository tagDetailRepository, ProjectRepository projectRepository) {
        this.tagDetailRepository = tagDetailRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<TagDetailDto> getAllByProjectId(Long projectId) {
        List<TagDetailDto> tagDetailDtoList = tagDetailRepository.findTagDetailsId(projectId);
        if (tagDetailDtoList.isEmpty()) {
            throw new NotFoundException("Tag Details not found");
        }
        return tagDetailDtoList;
    }

    @Override
    public TagDetailDto getTagDetailById(Long id) {
        TagDetailDto tagDetailDto = tagDetailRepository.findTagDetailId(id);
        if (tagDetailDto == null) {
            throw new NotFoundException("Tag Details Not Found : " + id);
        }
        return tagDetailDto;
    }

    @Override
    public TagDetailDto createTagDetail(Long projectId, TagDetailRequest tagDetailRequest) {
        TagDetail tagDetail = new TagDetail();
        tagDetail.setProject(projectRepository.findById(tagDetailRequest.getProjectId()).orElseThrow(() -> new NotFoundException("Project NOT FOUND " + tagDetailRequest.getProjectId())));
        tagDetail.setTagName(tagDetailRequest.getTagName());

        return tagDetailRepository.findTagDetailId(tagDetailRepository.save(tagDetail).getTagDetailId());
    }

    @Override
    public TagDetailResponse updateTagDetailById(Long tagDetailId, TagDetailRequest tagDetailRequest) {
        TagDetailDto tagDetailDto = tagDetailRepository.findTagDetailId(tagDetailId);
        if (tagDetailDto == null) {
            throw new NotFoundException("Tag Detail not found : " + tagDetailId);
        }
        return tagDetailRepository.updateTagDetail(tagDetailId, tagDetailRequest);
    }

    @Override
    public String deleteTagDetail(Long id) {
        tagDetailRepository.deleteById(id);
        return "{\"result : ok\"}";
    }
}
