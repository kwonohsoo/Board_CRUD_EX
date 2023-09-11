package com.example.boardcrudex.domain.reply.controller;

import com.example.boardcrudex.domain.reply.dto.ReplyReq;
import com.example.boardcrudex.domain.reply.dto.ReplyRes;
import com.example.boardcrudex.domain.reply.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@Api(tags = "댓글")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/create")
    @ApiOperation(value = "등록")
    public ResponseEntity<ReplyRes> create(@RequestBody ReplyReq replyReq) {
        ReplyRes replyRes = replyService.create(replyReq);
        return new ResponseEntity<>(replyRes, HttpStatus.CREATED);
    }

    @GetMapping("/member/{memberId}")
    @ApiOperation(value = "회원이 작성한 모든 댓글 조회")
    public ResponseEntity<List<ReplyRes>> getAllRepliesByMemberId(@PathVariable Long memberId) {
        List<ReplyRes> replies = replyService.getAllRepliesByMemberId(memberId);
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    @GetMapping("/board/{boardId}")
    @ApiOperation(value = "게시글의 모든 댓글 조회")
    public ResponseEntity<List<ReplyRes>> getAllRepliesByBoardId(@PathVariable Long boardId) {
        List<ReplyRes> replies = replyService.getAllRepliesByBoardId(boardId);
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    @GetMapping("/page")
    @ApiOperation(value = "페이지별 조회")
    public ResponseEntity<Page<ReplyRes>> getPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ReplyRes> replyPage = replyService.getAllPage(pageable);
        return new ResponseEntity<>(replyPage, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "댓글 수정")
    public ResponseEntity<ReplyRes> update(@PathVariable Long id, @RequestBody ReplyReq replyReq) {
        ReplyRes replyRes = replyService.update(id, replyReq);
        return new ResponseEntity<>(replyRes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "댓글 삭제")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        replyService.delete(id);
        String message = "댓글이 삭제되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
