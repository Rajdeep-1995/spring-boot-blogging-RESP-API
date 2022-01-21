package com.springboot.blog.controller;

import com.springboot.blog.paylaod.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Comment resource controller")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Create comment REST API")
    @PostMapping("/v1/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                     @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<CommentDto>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get comments by post ID REST API")
    @GetMapping("/v1/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @ApiOperation(value = "Get single comment by post ID REST API")
    @GetMapping("/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
                                                     @PathVariable(value = "commentId") long commentId){
        return new ResponseEntity<CommentDto>(commentService.getCommentById(postId,commentId),HttpStatus.OK);
    }

    @ApiOperation(value = "Update comment by comment and post ID REST API")
    @PutMapping("/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById( @PathVariable(value = "postId") long postId,
                                                        @PathVariable(value="commentId") long commentId,
                                                         @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<CommentDto>(commentService.updateCommentById(postId,commentId,commentDto),HttpStatus.OK);
    }

    @ApiOperation(value = "Delete comment by post and comment ID REST API")
    @DeleteMapping("/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value="commentId") long commentId){
       commentService.deleteCommentById(postId,commentId);
       return new ResponseEntity<String>("Comment is deleted successfully",HttpStatus.OK);
    }

}
