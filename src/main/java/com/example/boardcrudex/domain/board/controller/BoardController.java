package com.example.boardcrudex.domain.board.controller;

import com.example.boardcrudex.domain.board.dto.BoardReq;
import com.example.boardcrudex.domain.board.dto.BoardRes;
import com.example.boardcrudex.domain.board.service.BoardService;
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
    public ResponseEntity<BoardRes> create(@RequestBody BoardReq boardReq) {
        BoardRes boardRes = boardService.create(boardReq);
        return new ResponseEntity<>(boardRes, HttpStatus.CREATED);
    }

    // R
    @GetMapping("/read")
    @ApiOperation(value = "전체 조회")
    public ResponseEntity<List<BoardRes>> getAll() {
        List<BoardRes> boardResList = boardService.getAll();
        return new ResponseEntity<>(boardResList, HttpStatus.OK);
    }

    @GetMapping("read/{id}")
    @ApiOperation(value = "게시글 번호별 조회")
    public ResponseEntity<BoardRes> get(@PathVariable("id") Long id) {
        BoardRes boardRes = boardService.getId(id);
        return new ResponseEntity<>(boardRes, HttpStatus.OK);
    }

    // U
    @PutMapping("/update/{id}")
    @ApiOperation(value = "수정")
    public ResponseEntity<BoardRes> update(@PathVariable("id") Long id, @RequestBody BoardReq boardReq) {
        BoardRes boardRes = boardService.update(id, boardReq);
        return new ResponseEntity<>(boardRes, HttpStatus.OK);
    }

    // D
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "삭제")
    public ResponseEntity<BoardRes> delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
