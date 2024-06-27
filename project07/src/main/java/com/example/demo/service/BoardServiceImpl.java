package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // 서비스 클래스로 지정
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository repository; // 사용할 리파지토리를 멤버로 선언

    // 상속받은 메소드 구현하기
    @Override
    public int register(BoardDTO dto) {

        System.out.println(dto);

        Board entity = dtoToEntity(dto); // 파라미터로 전달받은 dto를 엔티티로 변환
        repository.save(entity); // 리파지토리로 게시물 등록
        int newNo = entity.getNo();

        System.out.println(entity);

        return newNo; // 새로운 게시물의 번호 반환
    }

    @Override
    public List<BoardDTO> getList() {
        List<Board> result = repository.findAll(); // 데이터베이스에서 게시물 목록을 가져온다
        List<BoardDTO> list = new ArrayList<>();
        list = result.stream() // 리스트에서 스트림 생성
                .map(entity -> entityToDto(entity)) // 중간연산으로 엔티티를 dto로 변환
                .collect(Collectors.toList()); // 최종연산으로 결과를 리스트로 변환

        return list; // 화면에 필요한 dto 리스트 반환
    }

    @Override
    public BoardDTO read(int no) { // 인자로 게시물 번호 받기

        Optional<Board> result = repository.findById(no); // 특정 게시물 정보 가져오기

        if (result.isPresent()) {
            Board board = result.get();
            BoardDTO boardDTO = entityToDto(board); //엔티티를 DTO로 변환
            return boardDTO; //DTO 반환
        } else {
            return null;
        }

    }

    @Override
    public void modify(BoardDTO dto) {
        // 업데이트 하는 항목은 '제목', '내용'

        //전달받은 DTO에서 게시물 번호 꺼내고, 해당 게시물 조회
        Optional<Board> result = repository.findById(dto.getNo()); 
        if (result.isPresent()) { // 해당 게시물이 존재하는지 확인
            Board entity = result.get();

            // 기존 엔티티에서 제목과 내용만 변경
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());

            // 다시 저장
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
