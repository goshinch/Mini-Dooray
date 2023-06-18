package com.nhnacademy.TaskAPI.service.comment;

import com.nhnacademy.TaskAPI.dto.comment.CommentDto;
import com.nhnacademy.TaskAPI.dto.comment.CommentRequest;
import com.nhnacademy.TaskAPI.dto.comment.CommentResponse;
import com.nhnacademy.TaskAPI.entity.Comment;
import com.nhnacademy.TaskAPI.exception.NotFoundException;
import com.nhnacademy.TaskAPI.repository.comment.CommentRepository;
import com.nhnacademy.TaskAPI.repository.task.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service("commentService")
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public CommentServiceImpl(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<CommentDto> getFindAllByTaskId(Long taskId) {
        List<CommentDto> commentDtoList = commentRepository.findCommentAllByTaskId(taskId);
        if (commentDtoList.isEmpty()) {
            throw new NotFoundException("Comment not found");
        }
        return commentDtoList;
    }

    @Override
    public CommentDto getComment(Long id) {
        CommentDto commentDto = commentRepository.findCommentById(id);
        if (commentDto == null) {
            throw new NotFoundException("Comment not found : " + id);
        }
        return commentDto;
    }

    @Override
    public CommentDto createComment(Long taskId, CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setTask(taskRepository.findById(commentRequest.getTaskId()).orElseThrow(() -> new NotFoundException("Comment not found : " + commentRequest.getCommentId())));
        comment.setUserId(commentRequest.getUserId());
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        return commentRepository.findCommentById(commentRepository.save(comment).getCommentId());
    }

    @Override
    public CommentResponse updateComment(Long commentId, Long taskId, CommentRequest commentRequest) {
        CommentDto commentDto = commentRepository.findCommentById(commentId);
        if (commentDto == null) {
            throw new NotFoundException("Comment not found : " + commentId);
        }
        return commentRepository.updateCommentById(commentId, commentRequest);
    }

    @Override
    public String deleteComment(Long id) {
        commentRepository.deleteById(id);
        return "{\"result : ok\"}";
    }
}
