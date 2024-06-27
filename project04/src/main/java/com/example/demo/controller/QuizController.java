package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/quiz")
public class QuizController {

    @GetMapping("/q1")
    public String ex1(){
        return "/quiz/quiz1";
    }
    
    @GetMapping("/q2")
    public String ex2(){
        return "/quiz/quiz2";
    }

}
