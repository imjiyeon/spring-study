package com.example.demo.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.member.domain.MemberDto;
import com.example.demo.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository repository;

	@Override
	public MemberDto register(MemberDto dto) {
		// 아이디 중복 체크
		String id = dto.getId();
		MemberDto getDto = get(id); // 상세조회
		if (getDto != null) {
			log.info("아이디가 중복됩니다");
			log.info("사용자를 추가할수 없습니다");
			return null;
		}
		/* 비밀번호 암호화 로직추가 */
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(dto.getPassword());
		dto.setPassword(password);
		// 사용자 추가
		dto.setRegDate(new Date());
		dto.setUpdateDate(new Date());
		log.info("register......" + dto);
		MemberDto insertDto = repository.insert(dto);
		return insertDto;
	}

	@Override
	public List<MemberDto> getList() {
		log.info("get List......");
		return repository.selectList();
	}

	@Override
	public MemberDto get(String id) {
		log.info("get......" + id);
		return repository.select(id);
	}

	@Override
	public boolean modify(MemberDto dto) {
		MemberDto readDto = repository.select(dto.getId());
		if (readDto == null) {
			log.info("해당 회원은 존재하지않습니다.");
			return false;
		} else {
			dto.setUpdateDate(new Date());
			log.info("modify......" + dto);
			return repository.update(dto) == 1;
		}
	}

	@Override
	public boolean remove(String id) {
		MemberDto readDto = repository.select(id);
		if (readDto == null) {
			log.info("해당 회원은 존재하지않습니다.");
			return false;
		} else {
			log.info("remove...." + id);
			return repository.delete(id) == 1;
		}
	}

}
