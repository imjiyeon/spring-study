package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {
	
	List<MemberDTO> getList();
	
	boolean register(MemberDTO dto);

	MemberDTO read(String id);
	
	default MemberDTO entityToDto(Member entity) {
		MemberDTO dto = MemberDTO.builder()
				.id(entity.getId())
				.password(entity.getPassword())
				.name(entity.getName())
				.role(entity.getRole())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();

		return dto;
	}

	default Member dtoToEntity(MemberDTO dto) {
		Member entity = Member.builder()
				.id(dto.getId())
				.password(dto.getPassword())
				.name(dto.getName())
				.role(dto.getRole())
				.build();
		return entity;
	}

}
