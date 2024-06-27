# 파일첨부
```
1. 환경 설정
   WebConfig 클래스 -> 상대경로 설정
   application.properties -> 저장 폴더 경로
2. 게시물 도메인 클래스에 파일과 관련된 필드 추가
   BoardDTO -> 파일스트림 필드와파일이름 필드 추가
   Board -> 파일이름 필드 추가
3. 게시물 등록시 이미지 파일 첨부
   FileUtil -> 특정경로에 파일을 저장하고 저장경로를 반환하는 기능을 가짐
   BoardService -> entityToDto메소드 수정
   BoardServiceImpl -> register 메소드 수정
4. 게시물 상세페이지에서 이미지 출력
   board/register.html -> 이미지 파일 등록 (폼태그 속성을 잘 보세요!)  
   board/read.html -> 이미지 파일 보여주기 
```

# 기능
```
- 이미지 출력
- 파일 첨부
```