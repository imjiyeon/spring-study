package com.example.demo.board.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.board.domain.BoardDto;
import com.example.demo.board.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository repository;

	@Override 
	public BoardDto register(BoardDto dto) { /* 리턴타입 및 로직 수정 */
		dto.setRegDate(new Date()); //작성일을 현재시간으로 변경
		dto.setUpdateDate(new Date()); //수정일을 현재시간으로 변경
		
		log.info("register......" + dto);
		BoardDto newDto = repository.insert(dto);
		return newDto;
	}

	@Override
	public List<BoardDto> getList() {
		log.info("get List......");
		return repository.selectList();
	}

	@Override
	public BoardDto get(int no) {
		log.info("get......" + no);
		return repository.select(no);
	}

	@Override
	public boolean modify(BoardDto dto) { /* 로직 변경 */
		BoardDto readDto = repository.select(dto.getNo());
		if (readDto == null) {
			log.info("해당 게시물은 존재하지않습니다.");
			return false;
		} else {
			log.info("modify......" + dto);
			dto.setUpdateDate(new Date()); //수정일을 현재시간으로 변경
			return repository.update(dto) == 1;
		}
	}

	@Override
	public boolean remove(int no) {
		BoardDto readDto = repository.select(no);
		if (readDto == null) {
			log.info("해당 게시물은 존재하지않습니다.");
			return false;
		} else {
			log.info("remove...." + no);
			return repository.delete(no) == 1;
		}
	}

}
