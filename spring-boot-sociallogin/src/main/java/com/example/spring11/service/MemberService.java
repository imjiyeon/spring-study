package com.example.spring11.service;


import com.example.spring11.dto.BoardDTO;
import com.example.spring11.dto.MemberDTO;
import com.example.spring11.entity.Member;
import org.springframework.data.domain.Page;

public interface MemberService {
	
	Page<MemberDTO> getList(int pageNumber);
	
	boolean register(MemberDTO dto);

	MemberDTO read(String id);

	// 회원 정보 수정 메소드 추가
	void modify(MemberDTO dto);

	// 소셜 로그인한 이메일로 회원가입하는 메소드 추가
	MemberDTO saveSocialMember(String email);

	default MemberDTO entityToDto(Member entity) {
		MemberDTO dto = MemberDTO.builder()
				.id(entity.getId())
				.password(entity.getPassword())
				.name(entity.getName())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.role(entity.getRole())
				.fromSocial(entity.isFromSocial())
				.build();

		return dto;
	}

	default Member dtoToEntity(MemberDTO dto) {
		Member entity = Member.builder()
				.id(dto.getId())
				.password(dto.getPassword())
				.name(dto.getName())
				.role(dto.getRole())
				.fromSocial(dto.isFromSocial())
				.build();
		return entity;
	}

}
