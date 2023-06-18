package com.nhnacademy.TaskAPI.repository.tagDetail;

import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailDto;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailRequest;
import com.nhnacademy.TaskAPI.dto.tagDetail.TagDetailResponse;
import com.nhnacademy.TaskAPI.entity.QTagDetail;
import com.nhnacademy.TaskAPI.entity.TagDetail;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TagDetailRepositoryImpl extends QuerydslRepositorySupport implements TagDetailRepositoryCustom {
    public TagDetailRepositoryImpl() {
        super(TagDetail.class);
    }

    @Override
    public List<TagDetailDto> findTagDetailsId(Long projectId) {
        QTagDetail qTagDetail = QTagDetail.tagDetail;
        return from(qTagDetail)
                .select(Projections.constructor(
                        TagDetailDto.class,
                        qTagDetail.tagDetailId,
                        qTagDetail.project.projectId,
                        qTagDetail.tagName
                )).fetch();
    }

    @Override
    public TagDetailDto findTagDetailId(Long tagDetailId) {
        QTagDetail qTagDetail = QTagDetail.tagDetail;
        return from(qTagDetail).select(Projections.constructor(
                TagDetailDto.class,
                qTagDetail.tagDetailId,
                qTagDetail.project.projectId,
                qTagDetail.tagName
                )).fetchFirst();
    }

    @Override
    public TagDetailResponse updateTagDetail(Long tagDetailId, TagDetailRequest tagDetailRequest) {
        QTagDetail qTagDetail = QTagDetail.tagDetail;

        update(qTagDetail)
                .where(qTagDetail.tagDetailId.eq(tagDetailId))
                .set(qTagDetail.project.projectId, tagDetailRequest.getProjectId())
                .set(qTagDetail.tagName, tagDetailRequest.getTagName())
                .execute();

        return from(qTagDetail)
                .select(Projections.constructor(
                        TagDetailResponse.class,
                        qTagDetail.project.projectId,
                        qTagDetail.tagName
                )).where(qTagDetail.tagDetailId.eq(tagDetailId))
                .fetchFirst();
    }
}
