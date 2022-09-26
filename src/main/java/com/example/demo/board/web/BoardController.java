package com.example.demo.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {


    @RequestMapping("/board")
    public String board() {
        return "board";
    }

    @RequestMapping("/editor")
    public String editor(){
        return "editor";
    }

    @RequestMapping("/detail")
    public String detail(Model model) {
        return "boardDetail";
    }
}

