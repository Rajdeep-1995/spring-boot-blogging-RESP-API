package com.springboot.blog.paylaod;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(description = "PostResponse model information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    @ApiModelProperty(value = "Post content/PostDto (List)")
    private List<PostDto> content;

    @ApiModelProperty(value = "Page number")
    private int pageNo;

    @ApiModelProperty(value = "Page size")
    private int pageSize;

    @ApiModelProperty(value = "Total elements/total posts")
    private long totalElements;

    @ApiModelProperty(value = "Total pages")
    private int totalPages;

    @ApiModelProperty(value = "Last page (boolean)")
    private boolean last;
}
