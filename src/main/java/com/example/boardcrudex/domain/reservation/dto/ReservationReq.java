package com.example.boardcrudex.domain.reservation.dto;

import com.example.boardcrudex.domain.reservation.entity.Reservation;
import com.example.boardcrudex.global.user.entity.Member;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("예약 요청 정보")
public class ReservationReq {
    private Long id;

    private Long memberId;

    private Date startTime;

    private Date endTime;

    public Reservation toEntity() {
        Member member = Member.builder().id(memberId).build();
        return Reservation.builder()
                .id(id)
                .member(member)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
