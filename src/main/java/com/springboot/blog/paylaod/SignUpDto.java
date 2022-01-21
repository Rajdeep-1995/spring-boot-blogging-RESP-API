package com.springboot.blog.paylaod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Sign up/register model information")
@Data
public class SignUpDto {
    @ApiModelProperty(value = "Name of the user")
    private String name;

    @ApiModelProperty(value = "Username of the user")
    private String username;

    @ApiModelProperty(value = "Email address of the user")
    private String email;

    @ApiModelProperty(value = "Password of the user")
    private String password;
}
