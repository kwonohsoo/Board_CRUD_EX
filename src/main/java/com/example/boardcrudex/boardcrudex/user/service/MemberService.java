package com.example.boardcrudex.boardcrudex.user.service;

import com.example.boardcrudex.boardcrudex.user.dto.JoinReq;
import com.example.boardcrudex.boardcrudex.user.dto.JoinRes;
import com.example.boardcrudex.boardcrudex.user.dto.MemInfoRes;
import com.example.boardcrudex.boardcrudex.user.entity.Member;
import com.example.boardcrudex.boardcrudex.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원 가입
    @Transactional
    public JoinRes memberJoin(JoinReq joinReq) throws Exception {
        Member member = joinReq.toEntity();
        String encodedPassword = passwordEncoder.encode(joinReq.getPassword());
        member.setPassword(encodedPassword);
        member = memberRepository.save(member);

//        if (memberRepository.findByEmail(joinReq.getEmail()).isPresent()) {
//            throw new Exception("이미 존재하는 이메일입니다.");
//        }
//
//        if (!joinReq.getPassword().equals(joinReq.getCheckedPassword())) {
//            throw new Exception("비밀번호가 일치하지 않습니다.");
//        }

        return JoinRes.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }

    // 회원 정보(전체)
    public List<MemInfoRes> memInfoAllList() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> MemInfoRes.builder()
                        .id(member.getId())
                        .email(member.getEmail())
                        .name(member.getName())
                        .build())
                .collect(Collectors.toList());
    }

    // 회원 정보(개인)
    public MemInfoRes memInfo(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            return MemInfoRes.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .build();
        } else {
            throw new NoSuchElementException("회원을 찾을 수 없습니다.");
        }
    }

}
