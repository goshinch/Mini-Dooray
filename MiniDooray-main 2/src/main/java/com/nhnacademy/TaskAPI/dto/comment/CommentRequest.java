package com.nhnacademy.TaskAPI.dto.comment;

import lombok.Data;


@Data
public class CommentRequest {
    private Long commentId;
    private Long taskId;
    private Long userId;
    private String content;
}
