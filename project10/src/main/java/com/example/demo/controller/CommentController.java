package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService service;

	//게시물별 댓글 목록 조회
	@ResponseBody
	@GetMapping("/list")
	public List<CommentDTO> list(@RequestParam(name = "boardNo") int boardNo) {
		List<CommentDTO> commentlist = service.getListByBoardNo(boardNo);

		return commentlist;
	}
	
	@ResponseBody
	@PostMapping("/register")
	public HashMap<String,Boolean> register(CommentDTO dto, Principal principal) { // 인증 객체
		HashMap<String,Boolean> map = new HashMap<>();
		String id = principal.getName(); // 인증객체에서 사용자 아이디 꺼내기
		dto.setWriter(id);		
		service.register(dto);
		map.put("success", true);
		return map;
	}
	
	@ResponseBody
	@GetMapping("/remove")
	public HashMap<String,Boolean> remove(@RequestParam(name = "commentNo") int commentNo) {
		HashMap<String,Boolean> map = new HashMap<>();
		service.remove(commentNo);
		map.put("success", true);
		return map;
	}

}
