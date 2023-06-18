package com.nhnacademy.TaskAPI.service.comment;

import com.nhnacademy.TaskAPI.dto.comment.CommentDto;
import com.nhnacademy.TaskAPI.dto.comment.CommentRequest;
import com.nhnacademy.TaskAPI.dto.comment.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentDto> getFindAllByTaskId(Long taskId);
    CommentDto getComment(Long id);
    CommentDto createComment(Long taskId, CommentRequest commentRequest);
    CommentResponse updateComment(Long commentId, Long taskId, CommentRequest commentRequest);
    String deleteComment(Long id);
}
