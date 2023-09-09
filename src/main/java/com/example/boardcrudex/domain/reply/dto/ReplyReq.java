package com.example.boardcrudex.domain.reply.dto;

import com.example.boardcrudex.domain.board.entity.Board;
import com.example.boardcrudex.domain.reply.entity.Reply;
import com.example.boardcrudex.global.user.entity.Member;
import io.swagger.annotations.ApiModel;
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

    private String comment;

    private Long memberId;

    private Long boardId;


    public Reply toEntity() {
        Member member = Member.builder().id(memberId).build();
        Board board = Board.builder().id(boardId).build();
        return Reply.builder()
                .comment(comment)
                .member(member)
                .board(board)
                .build();
    }
}
