package com.example.boardcrudex.domain.board.service;

import com.example.boardcrudex.domain.board.dto.BoardReq;
import com.example.boardcrudex.domain.board.dto.BoardRes;
import com.example.boardcrudex.domain.board.entity.Board;
import com.example.boardcrudex.domain.board.repository.BoardRepository;
import com.example.boardcrudex.global.user.entity.Member;
import com.example.boardcrudex.global.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BoardRes create(BoardReq boardReq) {

        Member member = memberRepository.findById(boardReq.getWriterId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        Board board = boardReq.toEntity();
        board.setMember(member);

        boardRepository.save(board);

        return BoardRes.builder()
                .id(board.getId())
                .writerId(member.getId())
                .writerName(member.getName())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    public List<BoardRes> getAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(board -> BoardRes.builder()
                        .id(board.getId())
                        .writerId(board.getMember().getId())
                        .writerName(board.getMember().getName())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    public BoardRes getId(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();

            return BoardRes.builder()
                    .id(board.getId())
                    .writerId(board.getMember().getId())
                    .writerName(board.getMember().getName())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .build();
        } else {
            throw new NoSuchElementException("게시물을 찾을 수 없습니다.");
        }
    }

    @Transactional
    public BoardRes update(Long id, BoardReq boardReq) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.update(boardReq.getTitle(), boardReq.getContent());

            boardRepository.save(board);

            return BoardRes.builder()
                    .id(board.getId())
                    .writerId(board.getMember().getId())
                    .writerName(board.getMember().getName())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .build();
        } else {
            throw new NoSuchElementException("게시물을 찾을 수 없습니다.");
        }
    }

    @Transactional
    public void delete(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            boardRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("게시물을 찾을 수 없습니다.");
        }
    }
}
