package com.example.demo.controller;

import java.io.IOException;
import java.util.Locale;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 스프링에서 지원하는 다양한 객체 사용하여
 * 요청메세지의 헤더에서 메타데이터 조회하기
 * */

@Controller
public class ParamController1 {

    // GET localhost:8080/headers1
    @ResponseBody
    @RequestMapping("/headers1")
    public String headers1(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false)
                          String cookie
    ) {
        System.out.println("request=" + request); //요청 메세지
        System.out.println("response=" + response); //응답 메세지
        System.out.println("httpMethod=" + httpMethod); // HTTP 메소드
        System.out.println("locale=" + locale); // 국가
        System.out.println("headerMap=" + headerMap); // 모든 헤더
        System.out.println("header host=" + host); // 특정 헤더

        return "ok";
    }

    // GET localhost:8080/headers2?username=둘리&age=20
    @RequestMapping("/headers2")
    public void headers2(HttpServletRequest request,HttpServletResponse response) throws IOException {
        
    	// request객체를 사용하여 요청메세지에 담긴 URL주소에서 파라미터 꺼내기
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println("username="+username+",age="+age);
        
        // response객체를 사용하여 응답메세지의 바디에 문자열 데이터 담기
        response.getWriter().write("ok");
    }

}