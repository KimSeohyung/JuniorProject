package com.example.demo.board.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    BoardEntity  findByBoardNum(int boardNum);
}

