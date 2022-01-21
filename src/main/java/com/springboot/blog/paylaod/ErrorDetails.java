package com.springboot.blog.paylaod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@ApiModel(description = "ErrorDetails model information")
@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    @ApiModelProperty(value = "Error timestamp")
    private Date timestamp;

    @ApiModelProperty(value = "Error message")
    private String message;

    @ApiModelProperty(value = "Error details")
    private String details;

}
