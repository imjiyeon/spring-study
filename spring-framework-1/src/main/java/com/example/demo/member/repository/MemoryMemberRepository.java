package com.example.demo.member.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.member.domain.MemberDto;

@Repository
public class MemoryMemberRepository implements MemberRepository {
	
	private static Map<String, MemberDto> store = new HashMap<String, MemberDto>();
	
	@Override
	public MemberDto insert(MemberDto dto) {
		store.put(dto.getId(), dto);
		return dto;
	}

	@Override
	public List<MemberDto> selectList() {
		return new ArrayList<MemberDto>(store.values());
	}

	@Override
	public MemberDto select(String id) {
		return store.get(id);
	}

	@Override
	public int update(MemberDto dto) {
		MemberDto updateDto = store.replace(dto.getId(), dto);
		if(updateDto == null) {
			return 0; 
		}else {
			return 1;
		}
	}

	@Override
	public int delete(String id) {
		MemberDto dto = store.remove(id);
		if(dto == null) {
			return 0; 
		}else {
			return 1;
		}
	}

}
