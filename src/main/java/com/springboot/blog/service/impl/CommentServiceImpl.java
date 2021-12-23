package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.paylaod.CommentDto;
import com.springboot.blog.respository.CommentRepository;
import com.springboot.blog.respository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = DTOtoEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return EntityToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> EntityToDto(comment)).collect(Collectors.toList());


    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

       Comment comment = checkCommentForPost(postId,commentId);

        return EntityToDto(comment);


    }

    @Override
    public CommentDto updateCommentById(long postId, long commentId, CommentDto commentDto) {
        Comment editedComment = DTOtoEntity(commentDto);
       Comment comment = checkCommentForPost(postId,commentId);

        if(!editedComment.getName().equals(""))
            comment.setName(editedComment.getName());
        if(!editedComment.getEmail().equals(""))
            comment.setEmail(editedComment.getEmail());
        if(!editedComment.getBody().equals(""))
            comment.setBody(editedComment.getBody());

        Comment updatedComment=commentRepository.save(comment);
        return EntityToDto(updatedComment);

    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
      Comment comment= checkCommentForPost(postId,commentId);
      commentRepository.delete(comment);

    }

    /*------------------------- private methods ------------------------------------*/

    /*------CommentDTO to Comment Entity------*/
    private Comment DTOtoEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto,Comment.class);//converting commentDto to comment entity
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }

    /*------Comment Entity to CommentDTO------*/
    private CommentDto EntityToDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);//converting comment entity to commentDto
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;

    }

    private Comment checkCommentForPost(long postId, long commentId){

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");

        return comment;
    }
}
