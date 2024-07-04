package com.example.demo.board.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.board.domain.BoardDto;
import com.example.demo.board.service.BoardService;

/*
 * 서비스를 사용하여 데이터를 처리하고,
 * 등록,조회,수정,삭제의 화면을 반환한다.
 * */

@Controller //컨트롤러 선언
@RequestMapping("/board") //공통 url
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/* 등록폼화면 */
	@GetMapping("/register")
	public String registerForm() {
		return "board/registerForm"; //등록폼 페이지 리턴
	}

	/* 등록처리 */
	@PostMapping("/register")
	public String register(BoardDto boardDto, RedirectAttributes rttr) {
		BoardDto registerDto = boardService.register(boardDto);
		rttr.addFlashAttribute("registerNo", registerDto.getNo()); //dto에서 글번호를 꺼내서 목록화면에 전달
		return "redirect:/board/list"; //목록조회 메소드 호출
	}

	/* 목록 */
	@GetMapping("/list")
	public String getList(Model model) { //모델전달자 사용
		List<BoardDto> boardList = boardService.getList();
		model.addAttribute("boardList", boardList); //게시물목록데이터 전달
		return "board/list"; //목록페이지 리턴
	}

	/* 상세 */
	@GetMapping("/read")
	public String get(@RequestParam int no, Model model) { //글번호 파라미터 수집
		BoardDto board = boardService.get(no);
		model.addAttribute("board", board); //게시물데이터 전달
		return "board/read"; //상세페이지 리턴
	}

	/* 수정폼화면 */
	@GetMapping("/modify") 
	public String modifyForm(@RequestParam int no, Model model) { //글번호 파라미터 수집
		BoardDto board = boardService.get(no);
		model.addAttribute("board", board); //게시물데이터 전달
		return "board/modifyForm"; //수정폼 페이지 리턴
	} 
	
	/* 수정처리 */
	@PostMapping("/modify") 
	public String modify(BoardDto boardDto, RedirectAttributes rttr) { //변경된 게시물데이터 파라미터 수집
		 boolean result = boardService.modify(boardDto);
		 rttr.addFlashAttribute("modifyResult", result); //상세화면에 수정결과 전달
		 return "redirect:/board/read?no="+boardDto.getNo(); //상세조회 메소드 호출
	} 
	
	/* 삭제처리 */
	@GetMapping("/remove") 
	public String remove(@RequestParam int no, RedirectAttributes rttr) { //글번호 파라미터 수집
		 boolean result = boardService.remove(no);
		 rttr.addFlashAttribute("removeResult", result); //목록화면에 삭제결과 전달
		 return "redirect:/board/list"; //목록 메소드 호출
	}

}

