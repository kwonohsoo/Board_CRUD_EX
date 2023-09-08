package com.example.boardcrudex.domain.board.dto;

import com.example.boardcrudex.domain.board.entity.Board;
import com.example.boardcrudex.global.user.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "게시판 요청 정보")
public class BoardReq {

    @ApiModelProperty(position = 1, value = "회원 번호", example = "1")
    private Long writerId;

    @ApiModelProperty(position = 2, value = "제목", example = "제목")
    private String title;

    @ApiModelProperty(position = 3, value = "내용", example = "내용")
    private String content;

    public Board toEntity() {
        Member member = Member.builder().id(writerId).build();
        return Board.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }
}
