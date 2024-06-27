package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Memo;

/*
 * 메모리 리자피토리의 쿼리메소드를 사용해서 
 * 메모테이블에서 조건검색한 데이터를 가져오는지 확인한다.
 * */
@SpringBootTest
public class MemoRepositoryTest2 {

	@Autowired
	MemoRepository2 memoRepository;

	@Test
	public void 번호가10와20사이인_데이터검색() {
		List<Memo> list = memoRepository.findByNoBetween(10, 20);
		for (Memo memo : list) {
			System.out.println(memo);
		}
	}

	@Test
	public void 번호가10보다작은_데이터검색() {
		List<Memo> list = memoRepository.findByNoLessThan(10);
		for (Memo memo : list) {
			System.out.println(memo);
		}
	}

	@Test
	public void 텍스트가null이아닌_데이터검색() {
		List<Memo> list = memoRepository.findByTextIsNotNull();
		for (Memo memo : list) {
			System.out.println(memo);
		}
	}

	@Test
	public void 번호를기준으로역정렬한_데이터검색() {
		List<Memo> list = memoRepository.findAllByOrderByNoDesc();
		for (Memo memo : list) {
			System.out.println(memo);
		}
	}

	@Test
	public void 번호가5보다작은_데이터삭제() {
		memoRepository.deleteMemoByNoLessThan(5);
		// @Transactional이 없다면 에러남
		// deleteby는 기본적으로 롤백처리가 되어 결과가 반영되지 않음
	}
}
