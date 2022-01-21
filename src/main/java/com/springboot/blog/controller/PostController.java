package com.springboot.blog.controller;


import com.springboot.blog.entity.Post;
import com.springboot.blog.paylaod.PostDto;
import com.springboot.blog.paylaod.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Post Resource controller")
@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;


    //create a new post end point
    @ApiOperation(value = "Create post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //to get all posts end point
    @ApiOperation(value = "Get all posts by pagination and sorting REST API")
    @GetMapping("/v1/posts")
    public PostResponse findAllPosts(
        @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
        @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
        @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
        @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return postService.findAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @ApiOperation(value = "Find post by ID REST API")
    @GetMapping("/v1/posts/{id}")
    public ResponseEntity<PostDto> findPostById(@PathVariable(name="id") Long id){
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @ApiOperation(value = "Update post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/v1/posts/{id}")
    public ResponseEntity<PostDto> findByIdAndUpdate(@Valid @RequestBody PostDto postDto, @PathVariable(name="id") Long id){
        return new ResponseEntity<>(postService.updatePostById(postDto,id),HttpStatus.OK);
    }

    @ApiOperation(value = "Delete post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/posts/{id}")
    public ResponseEntity<String> findByIdAndDelete(@PathVariable(name="id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post has been deleted successfully",HttpStatus.OK);
    }
}
