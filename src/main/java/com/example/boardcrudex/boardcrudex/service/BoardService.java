package com.example.boardcrudex.boardcrudex.service;

import com.example.boardcrudex.boardcrudex.dto.RequestDto;
import com.example.boardcrudex.boardcrudex.dto.ResponseDto;
import com.example.boardcrudex.boardcrudex.entity.Board;
import com.example.boardcrudex.boardcrudex.repository.BoardRepository;
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
    public ResponseDto create(RequestDto requestDTO) {
        Board board = requestDTO.toEntity();

        boardRepository.save(board);

        return ResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    // R
    public List<ResponseDto> getAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(board -> ResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    public ResponseDto getId(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();

            return ResponseDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .build();
        } else {
            throw new NoSuchElementException("게시물을 찾을 수 없습니다.");
        }
    }

    // U
    public ResponseDto update(Long id, RequestDto requestDto) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.update(requestDto.getTitle(), requestDto.getContent());

            boardRepository.save(board);

            return ResponseDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .build();
        } else {
            throw new NoSuchElementException("게시물을 찾을 수 없습니다.");
        }
    }

    // D
    public void delete(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            boardRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("게시물을 찾을 수 없습니다.");
        }
    }
}
