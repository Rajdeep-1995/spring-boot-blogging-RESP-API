package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.paylaod.PostDto;
import com.springboot.blog.paylaod.PostResponse;
import com.springboot.blog.respository.PostRepository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert DTO to entity
        Post post = DtoToEntity(postDto);

        //saving data to database using JPA interface
        Post createdPost = postRepository.save(post);

        //convert entity to DTO
        PostDto postResponse = entityToDto(createdPost);

        return postResponse;


    }

    @Override
    public PostResponse findAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
//        List<Post> posts = postRepository.findAll();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);// creating pageable object
        Page<Post> posts = postRepository.findAll(pageable); //finding data based on page no and page size
        List<Post> postContent = posts.getContent();// retrieving content of fetched data
       List<PostDto> content = postContent.stream().map(post->entityToDto(post)).collect(Collectors.toList());

       PostResponse postResponse = new PostResponse();

       postResponse.setContent(content);
       postResponse.setPageNo(posts.getNumber());
       postResponse.setPageSize(posts.getSize());
       postResponse.setTotalElements(posts.getTotalElements());
       postResponse.setTotalPages(posts.getTotalPages());
       postResponse.setLast(posts.isLast());

       return postResponse;

    }

    @Override
    public PostDto findPostById(Long id) {
        Post foundPost=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        return entityToDto(foundPost);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {
        Post foundPost=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));

        foundPost.setTitle(postDto.getTitle());
        foundPost.setDescription(postDto.getDescription());
        foundPost.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(foundPost);

        return entityToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {

        Post foundPost=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));

        postRepository.delete(foundPost);
    }

    /*------------PostDto to Post entity-----------------*/
    private Post DtoToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }

    /*------------Post entity to PostDto-----------------*/
    private PostDto entityToDto(Post post){
        PostDto postResponse = modelMapper.map(post,PostDto.class);
//        PostDto postResponse = new PostDto();
//        postResponse.setId(post.getId());
//        postResponse.setTitle(post.getTitle());
//        postResponse.setContent(post.getContent());
//        postResponse.setDescription(post.getDescription());
        return postResponse;
    }


}
