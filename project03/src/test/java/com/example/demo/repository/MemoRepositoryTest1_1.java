package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Memo;

/*
 * 메모리 리자피토리를 사용하여
 * 메모테이블의 데이터를 등록, 조회, 수정, 삭제한다
 * */
@SpringBootTest
public class MemoRepositoryTest1_1 {

	@Autowired
	MemoRepository memoRepository;
	
	@Test
	public void 리파지토리객체를가져왔는지확인() {
		System.out.println("memoRepository = " + memoRepository); //주소가 출력된다면 스프링에서 리파지토리를 처리한 것
	}
	
	@Test
	public void 데이터등록() {
		Memo memo = new Memo(0,"새글입니다");  //no는 auto_increment조건이므로 빈값 넣기
		memoRepository.save(memo);	 //1번이 존재하는지 확인하고 insert 실행

		// insert 또는 update sql이 생성됨
	}

	@Test
	public void 데이터일괄등록() {
		List<Memo> list = new ArrayList<>();
		Memo memo1 = new Memo(0,"새글입니다");
		Memo memo2 = new Memo(0,null); //text는 null 입력 가능
		list.add(memo1);
		list.add(memo2);
		memoRepository.saveAll(list);
	}
	
	@Test
	public void 데이터단건조회() {
		Optional<Memo> result = memoRepository.findById(1); //조회결과를 optional로 반환. 결과가 존재하는지 확인하는 형태
		if(result.isPresent()) { //결과값이 있는지 확인
			Memo memo = result.get(); //값 꺼내기
			System.out.println(memo);
		}

		// find 함수를 사용하여 select sql이 생성됨
	}
	@Test
	public void 데이터전체조회() {
		List<Memo> list = memoRepository.findAll(); //조회결과를 리스트로 반환
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void 데이터수정() {
		Memo memo = new Memo(1,"글이수정되었습니다"); //1번 메모 객체 생성
		memoRepository.save(memo);	//1번이 존재하는지 확인하고 update 실행

		// select > update sql이 생성됨
		// 조회결과에따라 insert 또는 update가 수행됨
	}
	
	@Test
	public void 데이터삭제() {
		memoRepository.deleteById(1);	//1번이 존재하는지 확인하고 delete 실행
		// delete 함수를 실행하면 delete sql이 생성됨
	}
	
	@Test
	public void 데이터전체삭제() {
		memoRepository.deleteAll();		//테이블을 조회하고 모든 데이터를 삭제함

		//select를 한 후, 데이터개수 만큼 delete sql이 생성됨
	}

}
