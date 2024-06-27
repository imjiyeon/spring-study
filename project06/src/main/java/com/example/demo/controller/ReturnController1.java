package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.BookDTO;
import org.springframework.web.servlet.ModelAndView;

/*
 * 응답 - 뷰템플릿
 * */
@Controller
public class ReturnController1 {

    //반환타입이 ModelAndView이면 화면 경로를 지정하고, 화면에 전달할 데이터를 담을 수 있음
    @GetMapping("/return/ex1")
    public ModelAndView ex1() {
        ModelAndView modelAndView = new ModelAndView("return/sample")
                                        .addObject("data","banana");
        return modelAndView;
    }

    //반환타입이 String이면 html 경로를 직접 지정
    @GetMapping("/return/ex2")
    public String ex2(Model model) { // 화면에 데이터를 전달하는 객체
        model.addAttribute("data","banana");
        return "return/sample";
    }

    // 반환타입이 void면 URL경로가 파일경로가 됨
    @GetMapping("/return/sample")
    public void ex3(Model model) {
        model.addAttribute("data","banana");
    }

}
