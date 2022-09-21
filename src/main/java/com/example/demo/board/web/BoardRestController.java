package com.example.demo.board.web;

import com.example.demo.board.service.BoardService;
import com.example.demo.board.service.request.BoardInsertCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/boardlist")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        return "board";
    }
}
