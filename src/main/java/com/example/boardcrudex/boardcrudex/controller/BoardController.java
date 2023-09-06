package com.example.boardcrudex.boardcrudex.controller;

import com.example.boardcrudex.boardcrudex.dto.RequestDto;
import com.example.boardcrudex.boardcrudex.dto.ResponseDto;
import com.example.boardcrudex.boardcrudex.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "게시판")
public class BoardController {

    private final BoardService boardService;

    // C
    @PostMapping("/create")
    @ApiOperation(value = "등록")
    public ResponseEntity<ResponseDto> create(@RequestBody RequestDto requestDto) {
        ResponseDto responseDto = boardService.create(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // R
    @GetMapping("/read")
    @ApiOperation(value = "전체 조회")
    public ResponseEntity<List<ResponseDto>> getAll() {
        List<ResponseDto> responseDtoList = boardService.getAll();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("read/{id}")
    @ApiOperation(value = "게시글 번호별 조회")
    public ResponseEntity<ResponseDto> get(@PathVariable("id") Long id) {
        ResponseDto responseDto = boardService.getId(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // U
    @PutMapping("/update/{id}")
    @ApiOperation(value = "수정")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id, @RequestBody RequestDto requestDto) {
        ResponseDto responseDto = boardService.update(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // D
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "삭제")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
