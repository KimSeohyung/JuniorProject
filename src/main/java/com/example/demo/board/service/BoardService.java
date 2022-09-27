package com.example.demo.board.service;

import com.example.demo.board.entity.*;
import com.example.demo.board.service.request.BoardInsertCommand;
import com.example.demo.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    BoardInsertRepository boardinsertRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    public void boardInsert(BoardInsertCommand command) {
        boardinsertRepository.save(command);
    }

    public List<BoardEntity> findAll(){
        List<BoardEntity> boardList = boardRepository.findAll();
        return boardList;
    }

    public BoardEntity findOne(int boardNum){
        BoardEntity boardOne = boardRepository.findByBoardNum(boardNum);
        return boardOne;
    }

    public void deleteOne(int boardNum){
        boardRepository.deleteById(boardNum);
    }

    public void boardModi(BoardInsertCommand command)  {boardinsertRepository.save(command);}

    @Transactional
    public int updateView(int boardNum) {
        return boardRepository.updateView(boardNum);
    }

    public void likeInsert(LikeEntity likeEntity){


        likeRepository.save(likeEntity);
    }

    public int likeCheck(int boardNum,int userNum) {
        LikeEntity find =  likeRepository.findByBoardIdxAndUserNum(boardNum,userNum);

        if (find != null) {
            return 1;
        }else {
            return 0;
        }
    }

    public void likeDelete(int boardNum,int userNum) {
        LikeEntity find =  likeRepository.findByBoardIdxAndUserNum(boardNum,userNum);
        likeRepository.delete(find);
    }

    public int likeCnt(int boardNum) { return likeRepository.countByBoardIdx(boardNum); }

}
