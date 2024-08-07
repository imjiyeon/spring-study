# 구글 소셜 로그인

#### 1.구글 로그인 서비스를 사용하기 위한 설정
```
1) 구글 서비스에 내 프로젝트 등록
구글 API 콘솔에 접속
https://console.cloud.google.com 호출
프로젝트 선택 > 새 프로젝트 > 만들기
프로젝트 선택 >  방금 만든 프로젝트

사이드바 > API 및 서비스 > 사용자 인증 정보
+사용자 인증 정보 만들기 > OAuth 클라이언트 ID 선택 > 동의 화면 구성
외부 선택 > 만들기 
앱이름, 사용자지원이메일, 개발자 연락처 정보 입력

사용자에게 가져올 정보 선택
범위 추가 > email,profile,openid 선택 > 업데이트

등록 내용 확인하고 다시 대시보드로 돌아가기

------------------------------------

+사용자 인증 정보 만들기 > OAuth 클라이언트 ID 선택 

어플리케이션 유형 선택 (웹 어플리케이션)

승인된 리디렉션 URL 입력 (http://localhost:8080/login/oauth2/code/google)

클라이언트ID와 비밀번호 따로 저장하기
프로젝트 설정에 사용됨

2) 프로젝트에 구글 서비스 설정
라이브러리 추가
application.properties 파일에 클라이언트 정보 추가
SecurityConfig에 oauth 로그인 가능하도록 코드 추가

3) 구글 소셜 로그인 하기
로그인 화면을 호출하면 구글 로그인 링크가 추가되어 있음
localhost:8080 호출
```

#### 2.현재 프로젝트와 연동
```
1) 구글 소셜 로그인 커스텀 클래스(OAuthUserDetailsServiceImpl) 생성 
메인화면을 보면 사용자 정보가 출력되어 있는데,
클라이언트 아이디와 oauth 권한 등 알 수 없는 값이 출력되어 있음
우리가 기존에 사용하던 인증 객체와 구조가 다름
따라서 구글 로그인 결과를 일반 로그인 결과와 동일한 구조로 만들어야 함

2) 이메일을 이용해서 회원가입 처리
MemberService에 소셜 회원 가입 메소드 추가하여 
구글 로그인한 사용자를 우리 DB에 저장하기
인증객체에서 이메일 추출해서 아이디로 사용할 것

3) 기존 인증 클래스(CustomUser) 수정
OAuthUserService는 로그인 후 인증객체로 OAuth2User라는 객체를 반환함
이 객체는 우리가 기존에 사용하던 객체와 구조가 다름
OAuth2User를 CustomUser로 변환하는 생성자 추가

4) 회원 클래스(MemberDTO) 수정
소셜 회원 여부를 체크하는 필드 추가

5) 로그인 함수 처리
OAuthUserDetailsServiceImpl에서 로그인 함수 처리
구글 소셜 로그인을 한 경우에는, 해당 이메일로 가입한 회원이 있는지 확인
없다면 새로운 회원을 추가
서비스를 통해 가져온 회원정보를 CustomUser로 변환
마지막에 반환

6) 구글 소셜 로그인 하기
이제 구글 로그인과 일반 로그인 모두 같은 결과를 반환한다
메인화면에서 사용자 정보가 같은 구조로 출력되는 것을 확인 할 수 있다 
localhost:8080 호출

```

#### 3.로그인 후 처리
```
1) 로그인 성공 핸들러(CustomAuthenticationSuccessHandler) 생성
AuthenticationSuccessHandler을 상속받는 구현 클래스를 생성
소셜 로그인한 사용자의 정보 꺼내기
만약 패스워드가 초기 패스워드(1111) 이라면 회원정보 수정페이지로 이동
아니면 메인페이지로 이동

2) 핸들러 등록
SecurityConfig에서 oauth 로그인 기능에 핸들러 등록

3) 소셜 회원 계정으로 일반 로그인 하기
회원정보 수정페이지로 이동하는지 확인
비밀번호 수정하기

```