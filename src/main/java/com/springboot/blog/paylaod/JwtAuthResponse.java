package com.springboot.blog.paylaod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

@ApiModel(description = "JwtAuthResponse model information")
@NoArgsConstructor
public class JwtAuthResponse {

    @ApiModelProperty(value = "JWT access token")
    private String accessToken;

    @ApiModelProperty(value = "Access token type")
    private String tokenType = "Bearer";

    public JwtAuthResponse(String accessToken){
        this.accessToken=accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
