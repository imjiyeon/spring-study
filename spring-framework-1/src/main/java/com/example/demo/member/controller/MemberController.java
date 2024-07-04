package com.example.demo.member.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.member.domain.MemberDto;
import com.example.demo.member.service.MemberService;

@Controller
//request 맵핑 삭제
public class MemberController {

	@Autowired
	private MemberService service;

	/* 등록화면 */
	@GetMapping("/register")
	public String registerForm() {
		return "member/registerForm";
	}

	/* 등록처리 */
	@PostMapping(value = "/register")
	public String register(MemberDto dto, RedirectAttributes rttr) {
		MemberDto registerDto = service.register(dto);
		if(registerDto == null) {
			rttr.addFlashAttribute("resultMessage", "아이디가 중복되어 등록에 실패하였습니다");
			return "redirect:/register"; //request 수정
		}else {
//			return "redirect:/member/list";
			return "redirect:/customLogin"; /* 사용자 인증처리 로직 붙이고 난후에 request 수정 */
		}
	}

	/* 목록 */
	@GetMapping(value = "/member/list") //request 수정
	public String getList(Model model) {
		List<MemberDto> memberList = service.getList();
		model.addAttribute("memberList", memberList);
		return "member/list";
	}

	/* 상세 */
	@GetMapping(value = "/member/read") //request 수정
	public String get(@RequestParam String id, Model model) {
		MemberDto memberDto = service.get(id);
		model.addAttribute("member", memberDto);
		return "member/read";
	}

}
