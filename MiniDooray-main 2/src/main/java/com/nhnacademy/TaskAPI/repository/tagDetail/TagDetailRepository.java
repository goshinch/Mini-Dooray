package com.nhnacademy.TaskAPI.repository.tagDetail;

import com.nhnacademy.TaskAPI.entity.TagDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDetailRepository extends JpaRepository<TagDetail,Long>, TagDetailRepositoryCustom {
}
