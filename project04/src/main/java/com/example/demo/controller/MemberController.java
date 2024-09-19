package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRepository;

// 회원 관리 요청을 처리하기 위한 컨트롤러 구현

@Controller //해당 클래스를 컨트롤러로 지정하고, 빈으로 등록
@RequestMapping("/v2") // 사용자 요청을 /v2 주소에 매핑 (중간경로)
public class MemberController {
	
	MemberRepository repository = new MemberRepository();
		
	// 회원 폼을 반환하는 메소드
    @GetMapping("/form") // GET 요청을 /form 주소에 매핑
    public String method1() {
        return "/v2/form"; 
        // /src/main/webapp/view/v2/form.jsp 파일을 반환
    }
    
    
    // 회원 등록을 처리하는 메소드
    // 사용자가 보낸 파라미터를 받아 처리
    @PostMapping("/save") // POST 요청을 /save 경로에 매핑
    public String method2(@RequestParam(name = "username") String username, 
    					  @RequestParam(name = "password") String password, 
    					  Model model) {

    	// 새로운 회원 객체 생성 후 저장소에 저장
        Member member = new Member(0, username, password);
        repository.save(member);
        
        // 추가된 회원 정보를 모델에 담아 JSP로 전달
        model.addAttribute("member", member);
    	
        return "/v2/save"; // 해당 경로의 JSP 파일을 반환
    }

}
