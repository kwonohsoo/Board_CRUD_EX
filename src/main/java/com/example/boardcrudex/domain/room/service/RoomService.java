package com.example.boardcrudex.domain.room.service;

import com.example.boardcrudex.domain.room.dto.RoomReq;
import com.example.boardcrudex.domain.room.dto.RoomRes;
import com.example.boardcrudex.domain.room.entity.Room;
import com.example.boardcrudex.domain.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomRes create(RoomReq roomReq) {
        Room room = roomReq.toEntity();
        Room savaRoom = roomRepository.save(room);

        return RoomRes.builder()
                .id(savaRoom.getId())
                .roomName(savaRoom.getRoomName())
                .build();
    }

    public List<RoomRes> roomList() {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream()
                .map(room -> RoomRes.builder()
                        .id(room.getId())
                        .roomName(room.getRoomName())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    public RoomRes getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 회의실을 찾을 수 없습니다."));
        return RoomRes.builder()
                .id(room.getId())
                .roomName(room.getRoomName())
                .build();
    }

    public RoomRes update(Long id, RoomReq roomReq) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.update(roomReq.getRoomName());

            roomRepository.save(room);

            return RoomRes.builder()
                    .id(room.getId())
                    .roomName(room.getRoomName())
                    .build();
        } else {
            throw new NoSuchElementException("해당 회의실을 찾을 수 없습니다.");
        }
    }

    public void delete(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            roomRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("해당 회의실을 찾을 수 없습니다.");
        }
    }
}
