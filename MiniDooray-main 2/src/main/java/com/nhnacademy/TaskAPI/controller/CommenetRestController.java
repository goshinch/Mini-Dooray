package com.nhnacademy.TaskAPI.controller;

import com.nhnacademy.TaskAPI.dto.comment.CommentDto;
import com.nhnacademy.TaskAPI.dto.comment.CommentRequest;
import com.nhnacademy.TaskAPI.dto.comment.CommentResponse;
import com.nhnacademy.TaskAPI.service.comment.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommenetRestController {

    private final CommentService commentService;

    public CommenetRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/list/{taskId}")
    public ResponseEntity<List<CommentDto>> getCommentList(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(commentService.getFindAllByTaskId(taskId));
    }

    @GetMapping("/{commentId}")
    public CommentDto getComment(@PathVariable("commentId") Long commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping("/new")
    public CommentDto createComment(@RequestParam("commentId")Long commentId,
                                    @RequestBody CommentRequest commentRequest) {
        return commentService.createComment(commentId, commentRequest);
    }

    @PatchMapping("/update/{commentId}")
    public CommentResponse updateComment(@PathVariable("commentId") Long commentId,
                                         @RequestParam("taskId") Long taskId,
                                         @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(commentId, taskId, commentRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestParam("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}
