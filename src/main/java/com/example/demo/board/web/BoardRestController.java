package com.example.demo.board.web;

import com.example.demo.board.entity.BoardEntity;
import com.example.demo.board.service.BoardService;
import com.example.demo.board.service.request.BoardInsertCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class BoardRestController {

    @Autowired
    BoardService boardService;

    @PostMapping("/boardAdd")
    public String boardAdd(BoardInsertCommand command){
        boardService.boardInsert(command);
        return "/board";
    }

    @GetMapping("/boardList")
    public List<BoardEntity> boardList(){
        return boardService.findAll();
    }

    @GetMapping("/detailOne/{boardNum}")
    public Optional<BoardEntity> detailOne(Model model, @PathVariable Integer boardNum){
        model.addAttribute("boardNum", boardNum);
        return boardService.findOne(boardNum);
    }

}
