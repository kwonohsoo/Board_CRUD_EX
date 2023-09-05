package com.example.boardcrudex.boardcrudex.controller;

import com.example.boardcrudex.boardcrudex.dto.RequestDto;
import com.example.boardcrudex.boardcrudex.dto.ResponseDto;
import com.example.boardcrudex.boardcrudex.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
@Api("게시판 컨트롤러")
public class BoardController {

    private final BoardService boardService;

    // C
    @PostMapping("/create")
    @ApiOperation(value = "등록")
    public ResponseEntity<ResponseDto> create(@RequestBody RequestDto requestDto) {
        ResponseDto responseDto = boardService.create(requestDto);
        return ResponseEntity.ok(responseDto);
    }



    // R



    // U



    // D



}
