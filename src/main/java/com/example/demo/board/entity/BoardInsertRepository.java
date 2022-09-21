package com.example.demo.board.entity;


import com.example.demo.board.service.request.BoardInsertCommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardInsertRepository extends JpaRepository<BoardInsertCommand,String> {
}
