package com.springboot.blog.paylaod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Login model information")
@Data
public class LoginDto {
    @ApiModelProperty(value = "Username or email address")
    private String usernameOrEmail;

    @ApiModelProperty(value = "Password")
    private String password;
}
