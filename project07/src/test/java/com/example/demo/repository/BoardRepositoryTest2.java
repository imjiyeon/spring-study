package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Board;


@SpringBootTest
public class BoardRepositoryTest2 {

	@Autowired
	BoardRepository repository;

	@Test
	public void 테스트용게시물30개추가() {
		
		for(int i=1; i<=30; i++) {
			Board board = Board.builder()
							.title(i+"번글").content("안녕하세요").writer("둘리")
							.build();
			repository.save(board);
		}	
	}

	@Test
	public void 페이징테스트() {
		
		//1페이지에 게시물 10개 설정
		// of 메소드로 객체 생성
		Pageable pageable = PageRequest.of(0,10); 
		Page<Board> result = repository.findAll(pageable);
		
		// 전체 페이지 정보 출력 (게시물 리스트 + 페이지 정보)
		System.out.println(result); 

		// 게시물 리스트만 출력
		List<Board> list = result.getContent();
		System.out.println(list); 
	}

	@Test
	public void 정렬조건추가하기() {
		// no 필드값을 기준으로 역정렬
		Sort sort = Sort.by("no").descending();

		// 정렬 조건 추가
		Pageable pageable = PageRequest.of(0,10, sort);
		Page<Board> result = repository.findAll(pageable);

		List<Board> list = result.getContent();
		System.out.println(list);
	}

}
