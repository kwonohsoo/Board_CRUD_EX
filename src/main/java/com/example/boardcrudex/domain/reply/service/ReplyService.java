package com.example.boardcrudex.domain.reply.service;

import com.example.boardcrudex.domain.board.entity.Board;
import com.example.boardcrudex.domain.board.repository.BoardRepository;
import com.example.boardcrudex.domain.reply.dto.ReplyReq;
import com.example.boardcrudex.domain.reply.dto.ReplyRes;
import com.example.boardcrudex.domain.reply.entity.Reply;
import com.example.boardcrudex.domain.reply.repository.ReplyRepository;
import com.example.boardcrudex.global.user.entity.Member;
import com.example.boardcrudex.global.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReplyRes create(ReplyReq replyReq) {
        Member member = memberRepository.findById(replyReq.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        Board board = boardRepository.findById(replyReq.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        Reply reply = replyReq.toEntity();
        reply.setMember(member);
        reply.setBoard(board);

        replyRepository.save(reply);

        return ReplyRes.builder()
                .id(reply.getId())
                .memberId(member.getId())
                .memberName(member.getName())
                .boardId(board.getId())
                .comment(reply.getComment())
                .build();
    }

    // 해당 회원이 작성한 모든 댓글
    public List<ReplyRes> getAllRepliesByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        List<Reply> replies = replyRepository.findAllByMember(member);

        return replies.stream()
                .map(reply -> ReplyRes.builder()
                        .id(reply.getId())
                        .memberId(memberId)
                        .memberName(reply.getMember().getName())
                        .boardId(reply.getBoard().getId())
                        .comment(reply.getComment())
                        .build())
                .collect(Collectors.toList());
    }

    // 해당 게시글의 모든 댓글
    public List<ReplyRes> getAllRepliesByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        List<Reply> replies = replyRepository.findAllByBoard(board);

        return replies.stream()
                .map(reply -> ReplyRes.builder()
                        .id(reply.getId())
                        .memberId(reply.getMember().getId())
                        .memberName(reply.getMember().getName())
                        .boardId(boardId)
                        .comment(reply.getComment())
                        .build())
                .collect(Collectors.toList());
    }

    public Page<ReplyRes> getAllPage(Pageable pageable) {
        Page<Reply> replyPage = replyRepository.findAll(pageable);
        return replyPage.map(reply -> ReplyRes.builder()
                .id(reply.getId())
                .memberId(reply.getMember().getId())
                .memberName(reply.getMember().getName())
                .boardId(reply.getBoard().getId())
                .comment(reply.getComment())
                .build());
    }

    public ReplyRes update(Long id, ReplyReq replyReq) {
        Optional<Reply> optionalReply = replyRepository.findById(id);
        if (optionalReply.isPresent()) {
            Reply reply = optionalReply.get();
            reply.update(replyReq.getComment());

            replyRepository.save(reply);

            return ReplyRes.builder()
                    .id(reply.getId())
                    .memberId(reply.getMember().getId())
                    .memberName(reply.getMember().getName())
                    .boardId(reply.getBoard().getId())
                    .comment(reply.getComment())
                    .build();
        } else {
            throw new NoSuchElementException("댓글을 찾을 수 없습니다.");
        }
    }

    public void delete(Long id) {
        Optional<Reply> optionalReply = replyRepository.findById(id);
        if (optionalReply.isPresent()) {
            replyRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("댓글을 찾을 수 없습니다.");
        }
    }
}
