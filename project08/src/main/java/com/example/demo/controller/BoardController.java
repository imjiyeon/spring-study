package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/board")   
public class BoardController {

	@Autowired
	private BoardService service;

//	@GetMapping("/main")
//	public void main() {
//	}

	// 기존의 목록 메소드 삭제 후 다시 만들기
	@GetMapping("/list")
	// 페이지 번호 파라미터 추가 (기본값은 1페이지)
	public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
		// 게시물 목록 조회 
		Page<BoardDTO> list = service.getList(page); 
		// 화면에 결과 데이터 전달 (게시물 리스트 + 페이지 정보)
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
	public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
		int no = service.register(dto);
		redirectAttributes.addFlashAttribute("msg", no);
		return "redirect:/board/list";
	}

	/* 상세화면 메소드 수정 */
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
