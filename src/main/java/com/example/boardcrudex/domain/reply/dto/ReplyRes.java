package com.example.boardcrudex.domain.reply.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "댓글 응답 정보")
public class ReplyRes {

    @ApiModelProperty(position = 1, value = "댓글 번호", example = "1")
    private Long id;

    @ApiModelProperty(position = 2, value = "회원 번호", example = "1")
    private Long memberId;

    @ApiModelProperty(position = 3, value = "회원 이름", example = "tester")
    private String memberName;

    @ApiModelProperty(position = 4, value = "게시글 번호", example = "1")
    private Long boardId;

    @ApiModelProperty(position = 5, value = "댓글", example = "댓글")
    private String comment;

    @Builder
    public ReplyRes(Long id, String comment, Long memberId, String memberName, Long boardId) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.boardId = boardId;
        this.comment = comment;
    }
}
