package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    BoardService service;

    // 메인화면
    @GetMapping("/main")
    public void main() {
    }

    // 목록화면
    @GetMapping("/list")
    public void list(Model model) {
        List<BoardDTO> list = service.getList(); // 서비스로 게시물 목록 가져오기
        model.addAttribute("list", list); // 화면에 게시물 리스트 전달
    }

    // 등록화면
    @GetMapping("/register")
    public void register() {
    }

    // 등록처리
    @PostMapping("/register")
    // RedirectAttributes: 리다이렉트할 때 데이터를 전달하는 객체 (모델)
    // 화면에서 전달한 게시물 정보를 파라미터로 수집
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {

        // 게시물 등록하고 새로운 게시물 번호 반환
        int no = service.register(dto);
        
        // 리다이레트된 페이지(목록화면)에 새로운 게시물 번호 전달
        redirectAttributes.addFlashAttribute("msg", no);
        
        // 목록 화면으로 리다이렉트
        // 리다이렉트: 새로운 URL을 다시 호출하는 것
        return "redirect:/board/list"; //HTML파일X URL주소O
    }

    // 상세화면
    @GetMapping("/read")
    public void read(@RequestParam(name = "no") int no, Model model) {
        BoardDTO dto = service.read(no);
        model.addAttribute("dto", dto);
    }

    // 수정화면
    @GetMapping("/modify")
    public void modify(@RequestParam(name = "no") int no, Model model) {
        BoardDTO dto = service.read(no); // 게시물 번호로 조회
        model.addAttribute("dto", dto); // 화면에 게시물 정보 전달
    }

    // 수정처리
    @PostMapping("/modify")
    public String modifyPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
        // 게시물 수정
        service.modify(dto);
        // 리다이렉트 주소에 파라미터 추가 (?no=1)
        redirectAttributes.addAttribute("no", dto.getNo());
        // 상세화면으로 이동
        return "redirect:/board/read";

        // addFlashAttribute(): 리다이렉트할 화면에 데이터를 보내는 함수
        // addAttribute(): 리다이렉트 주소에 파라미터를 추가하는 함수
    }

    // 삭제처리
    @PostMapping("/remove")
    public String removePost(int no) {
        service.remove(no);
        return "redirect:/board/list";
    }

}
