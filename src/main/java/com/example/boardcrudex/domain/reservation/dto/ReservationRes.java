package com.example.boardcrudex.domain.reservation.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(value = "예약 응답 정보")
public class ReservationRes {

    private Long id;

    private Long roomId;

    private Long memberId;

    private Date startTime;



    private Date endTime;

    @Builder
    public ReservationRes(Long id, Long roomId, Long memberId, Date startTime, Date endTime) {
        this.id = id;
        this.roomId = roomId;
        this.memberId = memberId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
