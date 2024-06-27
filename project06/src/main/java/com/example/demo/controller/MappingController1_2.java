//package com.example.demo.controller;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
///*
// * 중복되는 URL은 클래스 레벨로 이동
// * */
//
//
//@RequestMapping("/board") // 중복되는 부분을 중간경로로 적용
//@Controller
//public class MappingController1_2 {
//
//    @ResponseBody
//    @GetMapping
//    public String list() {
//        System.out.println("게시물 조회..");
//		  return "ok";
//    }
//
//    @ResponseBody
//    @PostMapping
//    public String save() {
//        System.out.println("게시물 등록..");
//		  return "ok";
//    }
//
//    @ResponseBody
//    @PutMapping
//    public String modify() {
//        System.out.println("게시물 수정..");
//		  return "ok";
//    }
//
//    @ResponseBody
//    @DeleteMapping
//    public String remove() {
//        System.out.println("게시물 삭제..");
//		  return "ok";
//    }
//
//}
//
