package com.example.demo.board.service;

import com.example.demo.board.entity.BoardEntity;
import com.example.demo.board.entity.BoardInsertRepository;
import com.example.demo.board.entity.BoardRepository;
import com.example.demo.board.service.request.BoardInsertCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardInsertRepository boardinsertRepository;

    @Autowired
    BoardRepository boardRepository;

    public void boardInsert(BoardInsertCommand command) {
        boardinsertRepository.save(command);
    }

    public List<BoardEntity> boardList(){
        return boardRepository.findAll();
    }


}
