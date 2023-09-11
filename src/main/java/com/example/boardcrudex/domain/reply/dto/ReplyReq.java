package com.example.boardcrudex.domain.reply.dto;

import com.example.boardcrudex.domain.board.entity.Board;
import com.example.boardcrudex.domain.reply.entity.Reply;
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
@ApiModel(value = "댓글 요청 정보")
public class ReplyReq {

    @ApiModelProperty(position = 1, value = "회원 번호", example = "1")
    private Long memberId;

    @ApiModelProperty(position = 2, value = "게시글 번호", example = "1")
    private Long boardId;

    @ApiModelProperty(position = 3, value = "댓글", example = "댓글")
    private String comment;


    public Reply toEntity() {
        Member member = Member.builder().id(memberId).build();
        Board board = Board.builder().id(boardId).build();
        return Reply.builder()
                .member(member)
                .board(board)
                .comment(comment)
                .build();
    }
}
