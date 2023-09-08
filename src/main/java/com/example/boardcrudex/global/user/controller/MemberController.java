package com.example.boardcrudex.global.user.controller;

import com.example.boardcrudex.global.user.dto.JoinReq;
import com.example.boardcrudex.global.user.dto.JoinRes;
import com.example.boardcrudex.global.user.dto.MemInfoReq;
import com.example.boardcrudex.global.user.dto.MemInfoRes;
import com.example.boardcrudex.global.user.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Api(tags = "회원 관리")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<JoinRes> registerMember(@RequestBody JoinReq joinReq) {
        JoinRes joinRes = memberService.memberJoin(joinReq);
        return new ResponseEntity<>(joinRes, HttpStatus.CREATED);
    }

    @GetMapping("/allMemInfo")
    @ApiOperation(value = "회원 전체 정보")
    public ResponseEntity<List<MemInfoRes>> memInfoList() {
        List<MemInfoRes> memInfoRes = memberService.memInfoAllList();
        return new ResponseEntity<>(memInfoRes, HttpStatus.OK);
    }

    @GetMapping("/memInfo/{id}")
    @ApiOperation(value = "회원 개인 정보")
    public ResponseEntity<MemInfoRes> memInfo(@PathVariable("id") Long id) {
        MemInfoRes memInfoRes = memberService.memInfo(id);
        return new ResponseEntity<>(memInfoRes, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "회원 정보 수정")
    public ResponseEntity<MemInfoRes> update(@PathVariable("id") Long id, @RequestBody MemInfoReq memInfoReq) {
        MemInfoRes memInfoRes = memberService.update(id, memInfoReq);
        return new ResponseEntity<>(memInfoRes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "회원 탈퇴")
    private ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            memberService.delete(id);
            return ResponseEntity.ok("회원을 비활성화 했습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원을 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 중 오류가 발생했습니다.");
        }
    }
}
