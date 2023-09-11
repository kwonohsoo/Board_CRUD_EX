package com.example.boardcrudex.domain.room.controller;

import com.example.boardcrudex.domain.room.dto.RoomReq;
import com.example.boardcrudex.domain.room.dto.RoomRes;
import com.example.boardcrudex.domain.room.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Api(tags = "회의실")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    @ApiOperation(value = "회의실 생성")
    public ResponseEntity<RoomRes> create(@RequestBody RoomReq roomReq) {
        RoomRes room = roomService.create(roomReq);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "회의실 전체 조회")
    public ResponseEntity<List<RoomRes>> getAllRoom() {
        List<RoomRes> roomList = roomService.roomList();
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "회의실 개별 조회")
    public ResponseEntity<RoomRes> getRoomById(@PathVariable Long id) {
        RoomRes roomList = roomService.getRoomById(id);
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "회의실 수정")
    public ResponseEntity<RoomRes> update(@PathVariable Long id, @RequestBody RoomReq roomReq) {
        RoomRes roomRes = roomService.update(id, roomReq);
        return new ResponseEntity<>(roomRes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "회의실 삭제")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        roomService.delete(id);
        String message = id + " : 회의실이 삭제 되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
