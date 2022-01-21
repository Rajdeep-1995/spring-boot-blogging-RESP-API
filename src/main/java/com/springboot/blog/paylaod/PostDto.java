package com.springboot.blog.paylaod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel(description = "Post model information")
@Data
public class PostDto {
    @ApiModelProperty(value = "Post Id")
    private long id;

    @ApiModelProperty(value = "Post title")
    @NotEmpty
    @Size(min = 2,message = "title should have at least 2 characters")
    private String title;

    @ApiModelProperty(value = "Post description")
    @NotEmpty
    @Size(min = 10,message = "description should have at least 10 characters")
    private String description;

    @ApiModelProperty(value = "Post content/body")
    @NotEmpty
    private String content;
    private List<CommentDto> comments;
}
