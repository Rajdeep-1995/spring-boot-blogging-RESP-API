package com.springboot.blog.service;

import com.springboot.blog.paylaod.PostDto;
import com.springboot.blog.paylaod.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse findAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto findPostById(Long id);
    PostDto updatePostById(PostDto postDto,Long id);
    void deletePostById(Long id);
}
