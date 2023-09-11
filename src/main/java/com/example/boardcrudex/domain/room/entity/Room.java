package com.example.boardcrudex.domain.room.entity;

import com.example.boardcrudex.domain.reservation.entity.Reservation;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();

    public void update(String roomName) {
        this.roomName = roomName;
    }
}
