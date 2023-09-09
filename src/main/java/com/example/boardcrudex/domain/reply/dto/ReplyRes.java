package com.example.boardcrudex.domain.reply.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "댓글 응답 정보")
public class ReplyRes {

    private Long id;

    private String comment;

    private Long memberId;

    private String memberName;

    private Long boardId;

    @Builder
    public ReplyRes(Long id, String comment, Long memberId, String memberName, Long boardId) {
        this.id = id;
        this.comment = comment;
        this.memberId = memberId;
        this.memberName = memberName;
        this.boardId = boardId;
    }
}
