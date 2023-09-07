package com.example.boardcrudex.boardcrudex.user.controller;

import com.example.boardcrudex.boardcrudex.user.dto.JoinReq;
import com.example.boardcrudex.boardcrudex.user.dto.JoinRes;
import com.example.boardcrudex.boardcrudex.user.dto.MemInfoRes;
import com.example.boardcrudex.boardcrudex.user.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "회원 관리")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<JoinRes> registerMember(@RequestBody JoinReq joinReq) throws Exception {
        JoinRes joinRes = memberService.memberJoin(joinReq);
        return new ResponseEntity<>(joinRes, HttpStatus.CREATED);
    }

    @GetMapping("/allMemInfo")
    @ApiModelProperty(value = "전체 회원 정보")
    public ResponseEntity<List<MemInfoRes>> memInfoList() {
        List<MemInfoRes> memInfoRes = memberService.memInfoAllList();
        return new ResponseEntity<>(memInfoRes, HttpStatus.OK);
    }

    @GetMapping("/memInfo/{id}")
    @ApiModelProperty(value = "회원 개인 정보")
    public ResponseEntity<MemInfoRes> memInfo(@PathVariable("id") Long id) {
        MemInfoRes memInfoRes = memberService.memInfo(id);
        return new ResponseEntity<>(memInfoRes, HttpStatus.OK);
    }
}
