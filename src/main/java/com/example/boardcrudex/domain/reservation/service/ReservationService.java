package com.example.boardcrudex.domain.reservation.service;

import com.example.boardcrudex.domain.reservation.dto.ReservationReq;
import com.example.boardcrudex.domain.reservation.dto.ReservationRes;
import com.example.boardcrudex.domain.reservation.entity.Reservation;
import com.example.boardcrudex.domain.reservation.repository.ReservationRepository;
import com.example.boardcrudex.domain.room.dto.RoomRes;
import com.example.boardcrudex.domain.room.entity.Room;
import com.example.boardcrudex.domain.room.repository.RoomRepository;
import com.example.boardcrudex.global.user.entity.Member;
import com.example.boardcrudex.global.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    public List<ReservationRes> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservation -> ReservationRes.builder()
                        .id(reservation.getId())
                        .memberId(reservation.getMember().getId())
                        .roomId(reservation.getRoom().getId())
                        .startTime(reservation.getStartTime())
                        .endTime(reservation.getEndTime())
                        .build())
                .collect(Collectors.toList());
    }

//    public ReservationRes create(ReservationReq reservationReq) {
//        Long memberId = reservationReq.getMemberId();
//        Long roomId = reservationReq.getRoomId();
//        Date startTime = reservationReq.getStartTime();
//        Date endTime = reservationReq.getEndTime();
//
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));
//        Room room = roomRepository.findById(roomId)
//                .orElseThrow(() -> new EntityNotFoundException("회의실을 찾을 수 없습니다."));
//
//        if (isTime)
//
//    }

}
