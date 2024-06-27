package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository repository;

	@Override
	public int register(BoardDTO dto) {
		Board entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getNo();
	}

	// 기존 메소드 삭제
//	@Override
//	public List<BoardDTO> getList() {}

	// 목록 메소드 다시 만들기
	@Override
	public Page<BoardDTO> getList(int page) {
		
		// 페이지 번호를 인덱스로 변경. 페이지 인덱스는 0부터 시작됨
		int pageNum = (page == 0) ? 0 : page - 1;
		//페이지번호, 개수, 정렬방식을 입력하여 페이지 정보 생성
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("no").descending());
		//게시물 목록 조회
		Page<Board> entityPage = repository.findAll(pageable);
		//스트림을 사용하여 엔티티 리스트를 DTO 리스트로 변환
		Page<BoardDTO> dtoPage = entityPage.map( entity -> entityToDto(entity) );

		return dtoPage;
	}

	@Override
	public BoardDTO read(int no) {
        Optional<Board> result = repository.findById(no);
        if(result.isPresent()) {
        	Board board =  result.get();
        	return entityToDto(board);
        } else {
        	return null;
        }
	}

	@Override
	public void modify(BoardDTO dto) {
        Optional<Board> result = repository.findById(dto.getNo());
        if(result.isPresent()){
            Board entity = result.get();
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            repository.save(entity);
        }
	}

	@Override
	public int remove(int no) {

		Optional<Board> result = repository.findById(no);

		if (result.isPresent()) {
			repository.deleteById(no);
			return 1; //성공
		} else {
			return 0; //실패
		}

	}

}
