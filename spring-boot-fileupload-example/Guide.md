### 복습용 참고자료입니다

```
spring-boot-fileupload-template: 실습용 템플릿 프로젝트
spring-boot-fileupload-example: 완성된 예제 프로젝트

템플릿 프로젝트를 이클립스에 불러오고 실행 확인.

Board, BoardDTO에 파일 이름/스트림 필드 추가.

FileUtil 클래스 생성 → 파일 저장 메소드 작성.

application.properties에 파일 저장 경로 설정.

static/image 폴더 생성하고 실제 경로를 설정에 추가.

BoardServiceImpl에서 게시물 등록 시 파일 저장 처리 추가.

register.html에 파일 업로드 필드 추가, form에 enctype 작성.

게시물 등록 → 이미지 저장 확인 (static 폴더 기준).

이미지가 안 보이는 타이밍 이슈 발생 (static은 빌드시만 반영).

해결: C:/uploadfile 외부 폴더로 경로 변경.

WebConfig에서 /uploadfile/** → file:///C:/uploadfile/로 매핑.

read.html에서 이미지 경로를 웹루트 기준으로 수정 → 출력 확인.
```