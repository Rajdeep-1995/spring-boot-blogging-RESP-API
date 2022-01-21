package com.springboot.blog.paylaod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Comment model information")
@Data
public class CommentDto {

    @ApiModelProperty(value = "Comment Id")
    public long id;

    @ApiModelProperty(value = "Commenter name")
    @NotEmpty
    public String name;

    @ApiModelProperty(value = "Comment email address")
    @NotEmpty
    @Email
    public String email;

    @ApiModelProperty(value = "Comment body/content")
    @NotEmpty
    @Size(min = 10, message = "body must contain at least 10 characters")
    public String body;
}
