package com.nhnacademy.TaskAPI.repository.comment;

import com.nhnacademy.TaskAPI.dto.comment.CommentDto;
import com.nhnacademy.TaskAPI.dto.comment.CommentRequest;
import com.nhnacademy.TaskAPI.dto.comment.CommentResponse;
import com.nhnacademy.TaskAPI.entity.Comment;
import com.nhnacademy.TaskAPI.entity.QComment;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {
    public CommentRepositoryImpl() {
        super(Comment.class);
    }

    @Override
    public List<CommentDto> findCommentAllByTaskId(Long taskId) {
        QComment qComment = QComment.comment;

        return from(qComment)
                .select(Projections.constructor(
                        CommentDto.class,
                        qComment.commentId,
                        qComment.task.taskId,
                        qComment.userId,
                        qComment.content,
                        qComment.createdAt,
                        qComment.updatedAt
                ))
                .where(qComment.task.taskId.eq(taskId))
                .fetch();
    }

    @Override
    public CommentDto findCommentById(Long id) {
        QComment qComment = QComment.comment;

        return from(qComment)
                .select(Projections.constructor(
                        CommentDto.class,
                        qComment.commentId,
                        qComment.task.taskId,
                        qComment.userId,
                        qComment.content,
                        qComment.createdAt,
                        qComment.updatedAt
                ))
                .where(qComment.commentId.eq(id))
                .fetchFirst();
    }

    @Override
    public CommentResponse updateCommentById(Long id, CommentRequest commentRequest) {
        QComment qComment = QComment.comment;

        update(qComment)
                .where(qComment.commentId.eq(id))
                .set(qComment.task.taskId, commentRequest.getTaskId())
                .set(qComment.content, commentRequest.getContent())
                .set(qComment.updatedAt, LocalDateTime.now())
                .execute();

        return from(qComment)
                .select(Projections.constructor(
                        CommentResponse.class,
                        qComment.commentId,
                        qComment.task.taskId,
                        qComment.userId,
                        qComment.content,
                        qComment.createdAt,
                        qComment.updatedAt
                ))
                .where(qComment.commentId.eq(id))
                .fetchFirst();
    }
}

