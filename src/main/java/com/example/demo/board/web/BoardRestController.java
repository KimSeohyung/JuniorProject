package com.example.demo.board.web;

import com.example.demo.board.entity.BoardEntity;
import com.example.demo.board.service.BoardService;
import com.example.demo.board.service.request.BoardInsertCommand;
import com.example.demo.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class BoardRestController {

    @Autowired
    BoardService boardService;

    @PostMapping("/boardAdd")
    public String boardAdd(BoardInsertCommand command , @AuthenticationPrincipal PrincipalDetail principalDetail){
        command.setUser_num(principalDetail.getUserIdx());
        boardService.boardInsert(command);
        return "/board";
    }

    @GetMapping("/boardList")
    public List<BoardEntity> boardList(){
        return boardService.findAll();
    }

    @GetMapping("/detailOne/{boardNum}")
    public ModelAndView detailOne(@PathVariable int boardNum){
       BoardEntity boardOne = boardService.findOne(boardNum);
        ModelAndView mav = new ModelAndView("boardDetail");
        mav.addObject("boardOne", boardOne);
        return mav;
    }

    @GetMapping("/delete/{boardNum}")
    public void delete(@PathVariable int boardNum , @AuthenticationPrincipal PrincipalDetail principalDetail, HttpServletResponse response) throws IOException {
        String email = principalDetail.getUsername();
        int userIdx = principalDetail.getUserIdx();

        BoardEntity boardOne = boardService.findOne(boardNum);
        int dbUserIdx = boardOne.getMember().getUserIdx();

        if(userIdx == dbUserIdx) {
            System.out.println("삭제성공");
            boardService.deleteOne(boardNum);
        }else{
            System.out.println("삭제실패");
            response.sendRedirect("/board");
        }



    }


}
