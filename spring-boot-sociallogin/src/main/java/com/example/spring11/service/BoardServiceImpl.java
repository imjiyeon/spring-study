package com.example.spring11.service;


import com.example.spring11.dto.BoardDTO;
import com.example.spring11.entity.Board;
import com.example.spring11.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

	@Override
	public Page<BoardDTO> getList(int page) {
		
		int pageNum = (page == 0) ? 0 : page - 1;
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("no").descending());
		Page<Board> entityPage = repository.findAll(pageable);
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
			return 1;
		} else {
			return 0;
		}

	}

}
