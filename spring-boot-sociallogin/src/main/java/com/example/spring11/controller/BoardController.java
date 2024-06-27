package com.example.spring11.controller;

import com.example.spring11.dto.BoardDTO;
import com.example.spring11.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;

	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
		Page<BoardDTO> list = service.getList(page);
		model.addAttribute("list", list);	
		System.out.println("전체 페이지 수: " + list.getTotalPages());
		System.out.println("전체 게시물 수: " + list.getTotalElements());
		System.out.println("현재 페이지 번호: " + (list.getNumber() + 1));
		System.out.println("페이지에 표시할 게시물 수: " + list.getNumberOfElements());
	}

	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes, Principal principal) {
		String id = principal.getName();
		dto.setWriter(id);
		int no = service.register(dto);
		redirectAttributes.addFlashAttribute("msg", no);
		return "redirect:/board/list";
	}

	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, @RequestParam(defaultValue = "0", name = "page") int page, Model model) { //페이지 번호 파라미터 추가
		BoardDTO dto = service.read(no);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page); //화면에 페이지번호 전달
	}

	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {
		BoardDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	@PostMapping("/modify")
	public String modifyPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:/board/read";
	}

	@PostMapping("/remove")
	public String removePost(int no) {
		service.remove(no);
		return "redirect:/board/list";
	}

}
