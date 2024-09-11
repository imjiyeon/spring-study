package com.example.demo.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Memo;

/*
 * 페이징 및 정렬기능 사용하기
 * */
@SpringBootTest
public class MemoRepositoryTest1_2 {

	@Autowired
	MemoRepository repository;

	@Test
	void 메모데이터100개등록() {
		
		// rangeClosed: 특정 범위의 숫자들로 스트림을 생성하는 함수
		// forEach: 특정 작업을 수행하는 함수
		
		// 1부터 100까지의 숫자를 포함하는 스트림을 생성하고, 1부터 100까지의 숫자를 하나씩 출력
		IntStream.rangeClosed(1,100).forEach(i -> System.out.println(i));
		
		// 메모 100개를 생성해서 테이블에 추가
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Memo memo = Memo.builder().text("Sample.." + i).build();
			repository.save(memo);
		});

	}

	@Test
	void 페이징처리() {
		// of: Pageable 객체를 생성하는 함수
		// 페이지번호와 데이터개수를 담아서 페이지 생성
		Pageable pageable = PageRequest.of(0, 10); // 페이지번호 바꿔보기

		// 페이지 정보를 전달하여 데이터 조회하기
		// 그냥 조회하면 list로 반환되고
		// 페이징처리하면서 조회하면 Page객체가 반환됨 ()
		Page<Memo> page = repository.findAll(pageable);
//		List<Memo> list = repository.findAll();

		// 엔티티 목록 꺼내기
		List<Memo> list = page.getContent();
		System.out.println(list);

		// 페이지 부가 정보
		System.out.println("총 페이지:" + page.getTotalPages());
		System.out.println("현재 페이지 번호:" + page.getNumber());
		System.out.println("페이지당 데이터 개수:" + page.getSize());
		System.out.println("다음 페이지 존재 여부:" + page.hasNext());
		System.out.println("시작 페이지 여부:" + page.isFirst());
	}
	// SQL에 LIMIT이 추가됨
	// PageRequest.of(0, 10)를 사용하면 첫 번째 페이지를 조회한다
	// 이때 SQL의 LIMIT절은 "LIMIT 0, 10"이 된다
	// 첫 번째 행부터 시작해서 10개의 데이터를 조회한다는 의미이다
	// 만약 두 번째 페이지를 조회한다면 LIMIT절은 "LIMIT 10, 10"이 된다
	// 11 번째 행부터 시작해서 10개의 데이터를 조횐한다는 의미이다

	@Test
	void 정렬조건추가하기() {

		// no 필드를 기준으로 역정렬하는 조건을 생성
		Sort sort = Sort.by("no").descending();

		// 페이지번호, 데이터개수, 정렬조건을 담아서 페이지 정보 생성
		Pageable pageable = PageRequest.of(0, 10, sort);

		// 페이지 정보를 전달하여 데이터 조회
		Page<Memo> page = repository.findAll(pageable);

		List<Memo> list = page.getContent();

		// 메모번호를 기준으로 역정렬된 데이터가 조회됨
		for (Memo memo : list) {
			System.out.println(memo);
		}
	}
	// SQL에 ORDERBY가 추가됨
}
