package com.example.boardcrudex.domain.reply.repository;

import com.example.boardcrudex.domain.board.entity.Board;
import com.example.boardcrudex.domain.reply.entity.Reply;
import com.example.boardcrudex.global.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByBoard(Board board);

    List<Reply> findAllByMember(Member member);
}
