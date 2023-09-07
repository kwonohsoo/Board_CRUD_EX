package com.example.boardcrudex.boardcrudex.user.repository;

import com.example.boardcrudex.boardcrudex.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Object> findByEmail(String email);
}
