package com.example.boardcrudex.domain.room.dto;

import com.example.boardcrudex.domain.room.entity.Room;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "회의실 요청 정보")
public class RoomReq {

    @ApiModelProperty(value = "회의실", example = "회의실 A")
    private String roomName;

    public Room toEntity() {
        return Room.builder()
                .roomName(roomName)
                .build();
    }
}
