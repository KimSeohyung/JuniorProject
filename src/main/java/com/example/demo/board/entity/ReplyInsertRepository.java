package com.example.demo.board.entity;

import com.example.demo.board.service.request.ReplyInsertCommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyInsertRepository extends JpaRepository<ReplyInsertCommand, Integer> {
}
