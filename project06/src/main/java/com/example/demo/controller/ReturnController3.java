package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * 응답 - @RestController
 * */
//@RestController // @Controller + @ResponseBody가 포함됨
@Controller
@ResponseBody
@RequestMapping("/return3")
public class ReturnController3 {

    @GetMapping("/ex1")
    public String ex1() {
        return "Hi~";
    }

    @GetMapping("/ex2")
    public int ex2() {
        return 100;
    }

}
