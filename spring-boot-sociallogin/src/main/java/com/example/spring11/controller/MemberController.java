package com.example.spring11.controller;

import com.example.spring11.dto.BoardDTO;
import com.example.spring11.dto.MemberDTO;
import com.example.spring11.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	@GetMapping("/member/list")
	public void list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Page<MemberDTO> list = service.getList(page);
		model.addAttribute("list", list);	
	}

	@GetMapping("/register")
	public String register() {
		return "member/register";
	}

	@PostMapping("/register")
	public String registerPost(MemberDTO dto, RedirectAttributes redirectAttributes) {

		boolean isSuccess = service.register(dto);

		if(isSuccess) {
			return "redirect:/";
		}else {
			redirectAttributes.addFlashAttribute("msg", "아이디가 중복되어 등록에 실패하였습니다");
			return "redirect:/register";
		}

	}

	@GetMapping("/member/read")
	public void read(@RequestParam(name = "id") String id, @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		MemberDTO dto = service.read(id);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
	}

	// 회원 정보 수정 메소드 추가
	@GetMapping("/member/modify")
	public void modify(@RequestParam(name = "id") String id, Model model) {
		MemberDTO dto = service.read(id);
		model.addAttribute("dto", dto);
	}

	// 회원 정보 수정 메소드 추가
	@PostMapping("/member/modify")
	public String modifyPost(MemberDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("id", dto.getId());
		return "redirect:/member/read";
	}

}
