package com.example.boardcrudex.boardcrudex.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("로그인 응답 정보")
public class LoginRes {

    private String token;
}
