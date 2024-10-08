package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository repository;

	// 인코더 필드 선언
	@Autowired
	PasswordEncoder passwordEncoder;
		
	@Override
	public Page<MemberDTO> getList(int pageNumber) {
		int pageIndex = (pageNumber == 0) ? 0 : pageNumber - 1;
		Pageable pageable = PageRequest.of(pageIndex, 10, Sort.by("regDate").descending());
		Page<Member> entityPage = repository.findAll(pageable);
		Page<MemberDTO> dtoPage = entityPage.map( entity -> entityToDto(entity) );

		return dtoPage;
	}
	
	@Override
	public boolean register(MemberDTO dto) {
		String id = dto.getId();
		MemberDTO getDto = read(id);
		if (getDto != null) {
			System.out.println("사용중인 아이디입니다.");
			return false;
		}
		Member entity = dtoToEntity(dto);

		// 패스워드 인코더로 패스워드 암호화하기
		String enPw = passwordEncoder.encode(entity.getPassword());
		entity.setPassword(enPw);

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
