package com.example.demo.board.service;

import com.example.demo.board.entity.*;
import com.example.demo.board.service.request.BoardInsertCommand;
import com.example.demo.board.service.request.ReplyInsertCommand;
import com.example.demo.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    @Autowired
    ReplyInsertRepository replyInsertRepository;

    @Autowired
    ReplyRepository replyRepository;

    public void boardInsert(BoardInsertCommand command) {
        boardinsertRepository.save(command);
    }

    public void replyInsert(ReplyInsertCommand rcommand) {
        replyInsertRepository.save(rcommand);
    }

    public List<BoardEntity> findAll(){
        List<BoardEntity> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"boardNum"));
        return boardList;
    }

    public List<ReplyEntity> findByReplyBoardNum(int boardNum){
        List<ReplyEntity> replyList = replyRepository.getReplyEntitiesByBoardNumOrderByReplyRegidateDesc(boardNum);
        return replyList;
    }

    @Transactional
    public BoardEntity findOne(int boardNum){
        BoardEntity boardOne = boardRepository.findByBoardNum(boardNum);
        boardOne.updateViewCount(boardOne.getBoardViewcounts());
        return boardOne;
    }

    public void deleteOne(int boardNum){
        boardRepository.deleteById(boardNum);
    }

    public void deleteReply(int replyNum){
        replyRepository.deleteById(replyNum);
    }

    @Transactional
    public void boardModi(int boardNum, BoardInsertCommand command)  {
        boardinsertRepository.boardUpdate(command, boardNum);
    }

    public void likeInsert(LikeEntity likeEntity){
        BoardEntity board = new BoardEntity();
        int boardNum = likeEntity.getBoardIdx();

        likeRepository.save(likeEntity);
        boardRepository.likeUpdate(board,boardNum);
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

    public int likeCnt(int boardNum) { return boardRepository.likeCnt(boardNum); }

    public List<BoardEntity> bestBoard (int a){
        return boardRepository.findByLikeCntGreaterThan(a);
    }

}
