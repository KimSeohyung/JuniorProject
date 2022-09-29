package com.example.demo.board.entity;


import com.example.demo.board.service.request.BoardInsertCommand;
import com.example.demo.board.service.request.ReplyInsertCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface BoardInsertRepository extends JpaRepository<BoardInsertCommand,String> {

    @Modifying(clearAutomatically = true)
    @Query("update BoardEntity b set b.boardType = :#{#paramBoard.board_type}, b.boardTitle = :#{#paramBoard.board_title}, b.boardContents = :#{#paramBoard.board_contents}, b.boardModidate = :#{#paramBoard.board_modidate} where b.boardNum = :boardNum")
    int boardUpdate(@Param("paramBoard") BoardInsertCommand command, @Param("boardNum") int boardNum);
}

