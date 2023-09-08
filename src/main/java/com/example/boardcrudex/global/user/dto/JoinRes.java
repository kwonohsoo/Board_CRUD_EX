package com.example.boardcrudex.global.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "회원가입 응답 정보")
public class JoinRes {

    @ApiModelProperty(position = 3, value = "회원번호", example = "1")
    private Long id;

    @ApiModelProperty(position = 2, value = "이메일", example = "test@email.com")
    private String email;

    @ApiModelProperty(position = 3, value = "이름", example = "tester")
    private String name;

    @Builder
    public JoinRes(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
