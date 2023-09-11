package com.example.boardcrudex.domain.reservation.entity;

import com.example.boardcrudex.domain.room.entity.Room;
import com.example.boardcrudex.global.user.entity.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.util.Date;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Date startTime;

    private Date endTime;

    @PrePersist
    @PreUpdate
    private void validateTime() {
        if (startTime != null && endTime != null && startTime.after(endTime)) {
            throw new ValidationException("예약 시작 시간이 예약 종료 시간보다 이전일 수 없습니다.");
        }
    }
}
