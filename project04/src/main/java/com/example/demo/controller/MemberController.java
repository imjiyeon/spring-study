package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRepository;

/*
 * 리턴 타입이 string인 경우, 문자열 경로와 일치하는 jsp 파일을 찾아 반환
 * 파일경로: /src/main/webapp/view + /v1/form.jsp
 * */

@Controller //컨트롤러를 의미하는 어노테이션 + 빈으로 등록됨
@RequestMapping("/v2") // 사용자 요청을 /v2 주소로 매핑 (중간 경로)
public class MemberController {
	
	MemberRepository repository = new MemberRepository();
	
	// 회원 폼 메소드
    @GetMapping("/form") // get 요청을 /form 주소로 매핑
    public String method1() {
        return "/v2/form";
    }
    
    // 회원 등록 메소드
    // 사용자가 전달한 파라미터 받기
    @PostMapping("/save") // post 요청을 /save 주소로 매핑
    public String method2(@RequestParam(name = "username") String username, 
    								@RequestParam(name = "password") String password, Model model) {

        // 회원 추가
        Member member = new Member(0, username, password);
        repository.save(member);
        
        // 새로 추가한 회원 정보를 화면에 전달
        model.addAttribute("member", member);
    	
        return "/v2/save"; //사용자에게 반환할 jsp 파일의 경로
    }

}
