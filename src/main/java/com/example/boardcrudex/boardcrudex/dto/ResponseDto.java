package com.example.boardcrudex.boardcrudex.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "게시판 응답 정보")
public class ResponseDto {

    @ApiModelProperty(position = 1, value = "게시판 번호", example = "1")
    private Long id;

    @ApiModelProperty(position = 2, value = "제목", example = "제목")
    private String title;

    @ApiModelProperty(position = 3, value = "내용", example = "내용")
    private String content;

    @Builder
    public ResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
