package com.nhnacademy.TaskAPI.repository.comment;

import com.nhnacademy.TaskAPI.dto.comment.CommentDto;
import com.nhnacademy.TaskAPI.dto.comment.CommentRequest;
import com.nhnacademy.TaskAPI.dto.comment.CommentResponse;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CommentRepositoryCustom {
    List<CommentDto> findCommentAllByTaskId(Long taskId);
    CommentDto findCommentById(Long id);
    CommentResponse updateCommentById(Long id, CommentRequest commentRequest);
}
