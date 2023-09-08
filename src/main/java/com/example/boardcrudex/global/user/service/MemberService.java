package com.example.boardcrudex.global.user.service;

import com.example.boardcrudex.global.user.dto.JoinReq;
import com.example.boardcrudex.global.user.dto.JoinRes;
import com.example.boardcrudex.global.user.dto.MemInfoReq;
import com.example.boardcrudex.global.user.dto.MemInfoRes;
import com.example.boardcrudex.global.user.entity.Member;
import com.example.boardcrudex.global.user.exception.DuplicateEmailException;
import com.example.boardcrudex.global.user.exception.PasswordMismatchException;
import com.example.boardcrudex.global.user.repository.MemberRepository;
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
    public JoinRes memberJoin(JoinReq joinReq) throws DuplicateEmailException, PasswordMismatchException {

        if (memberRepository.findByEmail(joinReq.getEmail()).isPresent()) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        }

        if (!joinReq.getPassword().equals(joinReq.getCheckedPassword())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(joinReq.getPassword());

        Member member = joinReq.toEntity();
        member.setPassword(encodedPassword);
        member.setUseYn("0");
        member = memberRepository.save(member);

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
                        .useYn(member.getUseYn())
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
                    .useYn(member.getUseYn())
                    .build();
        } else {
            throw new NoSuchElementException("회원을 찾을 수 없습니다.");
        }
    }

    // 회원 정보 수정
    @Transactional
    public MemInfoRes update(Long id, MemInfoReq memInfoReq) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {

            if (!memInfoReq.getPassword().equals(memInfoReq.getCheckedPassword())) {
                throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");

            }
            String encodedPassword = passwordEncoder.encode(memInfoReq.getPassword());

            Member member = optionalMember.get();
            member.update(encodedPassword);

            memberRepository.save(member);

            return MemInfoRes.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .build();
        } else {
            throw new NoSuchElementException("회원을 찾을 수 없습니다.");
        }
    }

    // 회원 탈퇴
    @Transactional
    public void delete(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setUseYn("9");
            memberRepository.save(member);

        } else {
            throw new NoSuchElementException("회원을 찾을 수 없습니다.");
        }
    }
}
