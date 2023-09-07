package com.example.boardcrudex.boardcrudex.board.dto;

import com.example.boardcrudex.boardcrudex.board.entity.Board;
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

    @ApiModelProperty(position = 1, value = "제목", example = "제목")
    private String title;

    @ApiModelProperty(position = 2, value = "내용", example = "내용")
    private String content;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
