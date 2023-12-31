//package com.example.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.dto.BoardDTO;
//import com.example.demo.service.BoardService;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//@RequestMapping("/board")
//public class BoardController2 {
//
//	@Autowired
//	private BoardService service;
//	
//	@Operation(summary = "게시물 등록", description = "파라미터로 받은 게시물 정보를 등록합니다")
//	@PostMapping
//	public ResponseEntity<Integer> register(@RequestBody BoardDTO dto) {
//		log.info("게시물을 등록합니다.");
//		int no = service.register(dto);
//		return new ResponseEntity<>(no, HttpStatus.CREATED); //201성공코드와 새로운 글번호를 반환한다
//	}
//	
//	@Operation(summary = "게시물 목록 조회", description = "모든 게시물 정보를 조회합니다")
//	@GetMapping
//	public ResponseEntity<List<BoardDTO>> getList() {
//		log.info("게시물 목록을 조회합니다.");
//		List<BoardDTO> list = service.getList();
//		return new ResponseEntity<>(list, HttpStatus.OK); //200성공코드와 게시물목록을 반환한다
//	}
//	
//	@Operation(summary = "게시물 상세 조회", description = "파라미터로 받은 글번호로 게시물을 조회합니다")
//	@GetMapping("/{no}")
//	public ResponseEntity<BoardDTO> read(@PathVariable("no") int no) {
//		log.info("게시물을 상세 조회합니다.");
//		BoardDTO dto = service.read(no);
//		return new ResponseEntity<>(dto, HttpStatus.OK); //200성공코드와 게시물정보를 반환한다
//	}
//
//	@Operation(summary = "게시물 수정", description = "파라미터로 받은 게시물 정보로 교체합니다")
//	@PutMapping
//	public ResponseEntity modify(@RequestBody BoardDTO dto) {
//		 log.info("게시물을 수정합니다.");
//		 service.modify(dto);
//		 return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
//	} 
//
//	@Operation(summary = "게시물 삭제", description = "파라미터로 받은 글번호로 게시물을 삭제합니다")
//	@DeleteMapping("/{no}")
//	public ResponseEntity remove(@PathVariable("no") int no) {
//		log.info("게시물을 삭제합니다.");
//		service.remove(no);
//		return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
//	}
//	
//}
