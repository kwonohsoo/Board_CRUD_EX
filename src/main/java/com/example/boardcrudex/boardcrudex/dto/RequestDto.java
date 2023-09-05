package com.example.boardcrudex.boardcrudex.dto;

import com.example.boardcrudex.boardcrudex.entity.Board;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {

    private String title;

    private String content;


    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
