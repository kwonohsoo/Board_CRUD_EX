package com.example.boardcrudex.global.user.repository;

import com.example.boardcrudex.global.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Object> findByEmail(String email);
}
