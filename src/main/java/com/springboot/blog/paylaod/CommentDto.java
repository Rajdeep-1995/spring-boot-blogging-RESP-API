package com.springboot.blog.paylaod;

import lombok.Data;

@Data
public class CommentDto {
    public long id;
    public String name;
    public String email;
    public String body;
}
