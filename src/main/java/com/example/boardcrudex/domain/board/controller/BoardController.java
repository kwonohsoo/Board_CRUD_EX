package com.example.boardcrudex.domain.board.controller;

import com.example.boardcrudex.domain.board.dto.BoardReq;
import com.example.boardcrudex.domain.board.dto.BoardRes;
import com.example.boardcrudex.domain.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Api(tags = "게시판")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    @ApiOperation(value = "등록")
    public ResponseEntity<BoardRes> create(@RequestBody BoardReq boardReq) {
        BoardRes boardRes = boardService.create(boardReq);
        return new ResponseEntity<>(boardRes, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    @ApiOperation(value = "전체 조회")
    public ResponseEntity<List<BoardRes>> getAll() {
        List<BoardRes> boardResList = boardService.getAll();
        return new ResponseEntity<>(boardResList, HttpStatus.OK);
    }

    @GetMapping("/page")
    @ApiOperation(value = "페이지별 조회")
    public ResponseEntity<Page<BoardRes>> getPage(@RequestParam("page") int page, @PageableDefault(size = 5) @RequestParam("size") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<BoardRes> boardpage = boardService.getAllPage(pageable);
        return new ResponseEntity<>(boardpage, HttpStatus.OK);
    }

    @GetMapping("read/{id}")
    @ApiOperation(value = "게시글 번호별 조회")
    public ResponseEntity<BoardRes> get(@PathVariable("id") Long id) {
        BoardRes boardRes = boardService.getId(id);
        return new ResponseEntity<>(boardRes, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "수정")
    public ResponseEntity<BoardRes> update(@PathVariable("id") Long id, @RequestBody BoardReq boardReq) {
        BoardRes boardRes = boardService.update(id, boardReq);
        return new ResponseEntity<>(boardRes, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "삭제")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        String message = "게시글이 삭제되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
