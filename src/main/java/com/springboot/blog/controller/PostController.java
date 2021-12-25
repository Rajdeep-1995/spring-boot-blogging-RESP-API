package com.springboot.blog.controller;


import com.springboot.blog.entity.Post;
import com.springboot.blog.paylaod.PostDto;
import com.springboot.blog.paylaod.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;


    //create a new post end point
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //to get all posts end point
    @GetMapping
    public PostResponse findAllPosts(
        @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
        @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
        @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
        @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return postService.findAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findPostById(@PathVariable(name="id") Long id){
        return ResponseEntity.ok(postService.findPostById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PostDto> findByIdAndUpdate(@Valid @RequestBody PostDto postDto, @PathVariable(name="id") Long id){
        return new ResponseEntity<>(postService.updatePostById(postDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> findByIdAndDelete(@PathVariable(name="id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post has been deleted successfully",HttpStatus.OK);
    }
}
