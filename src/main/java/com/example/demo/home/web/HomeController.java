package com.example.demo.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home() {
        return "homes";
    }


}
