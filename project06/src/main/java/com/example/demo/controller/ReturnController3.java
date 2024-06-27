package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
 * 응답 - @RestController
 * */
@RestController // @Controller + @ResponseBody의 조합
@RequestMapping("/return3")
public class ReturnController3 {

    // 메소드에 @ResponseBody를 붙이지 않아도, 자동으로 적용됨
//    @ResponseBody
    @GetMapping("/ex1")
    public String ex1() {
        return "Hi~";
    }

    @GetMapping("/ex2")
    public int ex2() {
        return 100;
    }

	@GetMapping("/ex3")
    public BookDTO ex3() {
        BookDTO bookDto = new BookDTO("자바프로그래밍입문","한빛컴퍼니",20000);
        return bookDto;
    }

}
