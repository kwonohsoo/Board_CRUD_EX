package com.example.boardcrudex.boardcrudex.board.service;

import com.example.boardcrudex.boardcrudex.board.dto.BoardReq;
import com.example.boardcrudex.boardcrudex.board.dto.BoardRes;
import com.example.boardcrudex.boardcrudex.board.repository.BoardRepository;
import com.example.boardcrudex.boardcrudex.board.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // C
    @Transactional
    public BoardRes create(BoardReq boardReq) {
        Board board = boardReq.toEntity();

        boardRepository.save(board);

        return BoardRes.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    // R
    public List<BoardRes> getAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(board -> BoardRes.builder()
                        .id(board.getId())
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
                    .title(board.getTitle())
                    .content(board.getContent())
                    .build();
        } else {
            throw new NoSuchElementException("게시물을 찾을 수 없습니다.");
        }
    }

    // U
    @Transactional
    public BoardRes update(Long id, BoardReq boardReq) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.update(boardReq.getTitle(), boardReq.getContent());

            boardRepository.save(board);

            return BoardRes.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .build();
        } else {
            throw new NoSuchElementException("게시물을 찾을 수 없습니다.");
        }
    }

    // D
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
