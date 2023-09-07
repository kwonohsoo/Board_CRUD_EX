package com.example.boardcrudex.boardcrudex.user.dto;

import com.example.boardcrudex.boardcrudex.user.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "회원가입 요청 정보")
public class JoinReq {

    @ApiModelProperty(position = 1, value = "이메일", example = "test@email.com")
    private String email;

    @ApiModelProperty(position = 2, value = "비밀번호", example = "password")
    private String password;

    @ApiModelProperty(position = 3, value = "비밀번호 확인", example = "checkPassword")
    private String checkedPassword;

    @ApiModelProperty(position = 4, value = "이름", example = "tester")
    private String name;

    @Builder
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
