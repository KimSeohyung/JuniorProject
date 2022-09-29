package com.example.demo.board.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    BoardEntity  findByBoardNum(int boardNum);

    List<BoardEntity> findByLikeCntGreaterThan(int a);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update BoardEntity b set b.likeCnt = b.likeCnt + 1   where b.boardNum = :boardNum")
    int likeUpdate(@Param("board") BoardEntity board, @Param("boardNum") int boardNum);

    @Query("select b.likeCnt from BoardEntity b where b.boardNum = :boardNum")
    int likeCnt(@Param("boardNum") int boardNum);


}

