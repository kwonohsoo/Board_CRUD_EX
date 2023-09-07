package com.example.boardcrudex.boardcrudex.user.dto;

import com.example.boardcrudex.boardcrudex.user.entity.Member;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "로그인 요청 정보")
public class LoginReq {

    private String email;

    private String password;

    @Builder
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
