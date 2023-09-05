package com.example.boardcrudex.boardcrudex.service;

import com.example.boardcrudex.boardcrudex.dto.RequestDto;
import com.example.boardcrudex.boardcrudex.dto.ResponseDto;
import com.example.boardcrudex.boardcrudex.entity.Board;
import com.example.boardcrudex.boardcrudex.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.List;

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

//    // R
//    public List<ResponseDto> getAll() {
//
//    }
//
//    public ResponseDto getId(Long id) {
//
//    }
//
//
//
//    // U
//    public ResponseDto update(Long id, RequestDto requestDto) {
//
//    }
//
//
//
//    // D
//    public void delete(Long id) {
//
//    }
}
