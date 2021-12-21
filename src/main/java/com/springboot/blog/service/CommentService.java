package com.springboot.blog.service;

import com.springboot.blog.paylaod.CommentDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);
}
