package com.example.boardcrudex.global.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("회원 정보 요청")
public class MemInfoReq {

    @ApiModelProperty(position = 1, value = "비밀번호 수정", example = "updatePassword")
    private String password;

    @ApiModelProperty(position = 2, value = "비밀번호 수정 확인", example = "checkedUpdatePassword")
    private String checkedPassword;
}
