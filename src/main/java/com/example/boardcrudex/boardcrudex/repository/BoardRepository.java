package com.example.boardcrudex.boardcrudex.repository;

import com.example.boardcrudex.boardcrudex.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
