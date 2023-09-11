package com.example.boardcrudex.domain.room.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("회의실 응답 정보")
public class RoomRes {

    @ApiModelProperty(position = 1, value = "회의실 번호", example = "1")
    private Long id;

    @ApiModelProperty(position = 2, value = "회의실", example = "회의실 A")
    private String roomName;

    @Builder
    public RoomRes(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }
}
