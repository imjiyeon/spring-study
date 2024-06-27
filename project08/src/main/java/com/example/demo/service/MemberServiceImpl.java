package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService { //서비스 인터페이스 상속받기

	@Autowired
	private MemberRepository repository; //리파지토리 필드 선언
		
	@Override
	public Page<MemberDTO> getList(int pageNumber) {  //페이지 번호 받기
		int pageIndex = (pageNumber == 0) ? 0 : pageNumber - 1; //page index는 0부터 시작
		Pageable pageable = PageRequest.of(pageIndex, 10, Sort.by("regDate").descending());  //페이지번호, 회원데이터건수, 정렬방법을 입력하여 페이징 조건 생성
		Page<Member> entityPage = repository.findAll(pageable); //회원 목록을 페이지에 담아서 조회하기
		Page<MemberDTO> dtoPage = entityPage.map( entity -> entityToDto(entity) ); //엔티티 타입의 페이지를 DTO 타입으로 변환

		return dtoPage;
	}
	
	@Override
	public boolean register(MemberDTO dto) {
		// 아이디 중복 여부를 확인하고, 회원 등록을 진행
		String id = dto.getId();
		MemberDTO getDto = read(id);
		// 해당아이디가 사용되고 있다면, 처리결과는 실패(false) 반환
		if (getDto != null) {
			System.out.println("사용중인 아이디입니다.");
			return false;
		}
		// 해당아이디가 사용되지 않는다면, 회원을 등록하고 처리결과는 성공(true) 반환
		Member entity = dtoToEntity(dto);
		repository.save(entity);
		return true;
	}

	@Override
	public MemberDTO read(String id) {
		Optional<Member> result = repository.findById(id);
		if (result.isPresent()) {
			Member member = result.get();
			return entityToDto(member);
		} else {
			return null;
		}
	}

}
