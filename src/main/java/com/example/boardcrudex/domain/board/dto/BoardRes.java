package com.example.boardcrudex.domain.board.dto;

import com.example.boardcrudex.domain.reply.dto.ReplyRes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "게시판 응답 정보")
public class BoardRes {

    @ApiModelProperty(position = 1, value = "게시판 번호", example = "1")
    private Long id;

    @ApiModelProperty(position = 2, value = "회원 번호", example = "1")
    private Long writerId;

    @ApiModelProperty(position = 3, value = "회원 이름", example = "tester")
    private String writerName;

    @ApiModelProperty(position = 4, value = "제목", example = "제목")
    private String title;

    @ApiModelProperty(position = 5, value = "내용", example = "내용")
    private String content;

    @ApiModelProperty(position = 6, value = "댓글 목록", example = "댓글 목록 [ ]")
    private List<ReplyRes> replies;

    @Builder
    public BoardRes(Long id, Long writerId, String writerName, String title, String content, List<ReplyRes> replies) {
        this.id = id;
        this.writerId = writerId;
        this.writerName = writerName;
        this.title = title;
        this.content = content;
        this.replies = replies;
    }
}
