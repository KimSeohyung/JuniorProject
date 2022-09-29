package com.example.demo.board.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {

    List<ReplyEntity> getReplyEntitiesByBoardNumOrderByReplyRegidateDesc(Integer boardNum);
}
