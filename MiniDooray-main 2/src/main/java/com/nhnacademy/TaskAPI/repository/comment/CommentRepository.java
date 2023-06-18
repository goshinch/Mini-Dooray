package com.nhnacademy.TaskAPI.repository.comment;

import com.nhnacademy.TaskAPI.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
