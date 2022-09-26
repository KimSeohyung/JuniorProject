package com.example.demo.board.service;

import com.example.demo.board.entity.BoardEntity;
import com.example.demo.board.entity.BoardInsertRepository;
import com.example.demo.board.entity.BoardRepository;
import com.example.demo.board.service.request.BoardInsertCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardInsertRepository boardinsertRepository;

    @Autowired
    BoardRepository boardRepository;

    public void boardInsert(BoardInsertCommand command) {
        boardinsertRepository.save(command);
    }

    public List<BoardEntity> findAll(){
        List<BoardEntity> boardList = boardRepository.findAll();
        return boardList;
    }

    public Optional<BoardEntity> findOne(int boardNum){
        Optional<BoardEntity> boardOne = boardRepository.findById(boardNum);
        return boardOne;
    }

    public void deleteOne(int boardNum){
        boardRepository.deleteById(boardNum);
    }





}
