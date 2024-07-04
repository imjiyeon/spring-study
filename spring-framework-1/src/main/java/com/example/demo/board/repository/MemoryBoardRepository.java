package com.example.demo.board.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.board.domain.BoardDto;

@Repository
public class MemoryBoardRepository implements BoardRepository {
	
	private static Map<Integer, BoardDto> store = new HashMap<Integer, BoardDto>();
	
	private static int sequence = 0;

	@Override
	public BoardDto insert(BoardDto dto) { /* 리턴타입 변경 */
		dto.setNo(++sequence);
		store.put(dto.getNo(), dto);
		return dto; //새로운 dto를 전달
	}

	@Override
	public List<BoardDto> selectList() {
		return new ArrayList<BoardDto>(store.values());
	}

	@Override
	public BoardDto select(int no) {
		return store.get(no);
	}

	@Override
	public int update(BoardDto dto) {
		BoardDto updateDto = store.replace(dto.getNo(), dto);
		if(updateDto == null) { //수정 실패
			return 0; 
		}else {
			return 1;
		}
	}

	@Override
	public int delete(int no) {
		BoardDto dto = store.remove(no);
		if(dto == null) { //삭제 실패
			return 0; 
		}else {
			return 1;
		}
	}

}
