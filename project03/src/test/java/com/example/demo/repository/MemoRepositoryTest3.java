package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Memo;

/*
 * 메모리 리자피토리의 @Query어노테이션 메소드를 사용해서 
 * 메모테이블에서 조건검색한 데이터를 가져오는지 확인한다.
 * */
@SpringBootTest
public class MemoRepositoryTest3 {

	@Autowired
	MemoRepository3 memoRepository3;
	
	@Test
	public void 번호가3보다작은_메모검색() {
		List<Memo> list = memoRepository3.get1(3);
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void 텍스트가null이아닌_메모검색() {
		List<Memo> list = memoRepository3.get2();
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void 번호가2와3사이인_메모검색() {
		List<Memo> list = memoRepository3.get3(2, 3);
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void 번호를기준으로역정렬한_메모검색() {
		List<Memo> list = memoRepository3.get4();
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void 데이터삭제() {
		memoRepository3.delete1(15);
	}
	
	@Test
	public void 데이터수정() {
		Memo memo = new Memo(25,"변경했습니다");
		memoRepository3.update1(memo);
	}
	
}
