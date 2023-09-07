package com.example.boardcrudex.boardcrudex.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("회원 정보 응답")
public class MemInfoRes {

    @ApiModelProperty(position = 1, value = "회원 번호", example = "1")
    private Long id;

    @ApiModelProperty(position = 2, value = "이메일", example = "test@email.com")
    private String email;

    @ApiModelProperty(position = 3, value = "이름", example = "tester")
    private String name;

    @Builder
    public MemInfoRes(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
