package com.example.boardcrudex.boardcrudex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    private Long id;

    private String title;

    private String content;

    @Builder
    public ResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
