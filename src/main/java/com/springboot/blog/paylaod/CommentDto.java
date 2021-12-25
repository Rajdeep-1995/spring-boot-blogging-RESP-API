package com.springboot.blog.paylaod;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    public long id;
    @NotEmpty
    public String name;

    @NotEmpty
    @Email
    public String email;

    @NotEmpty
    @Size(min = 10, message = "body must contain at least 10 characters")
    public String body;
}
