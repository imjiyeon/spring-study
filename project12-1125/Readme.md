### 12장. API 서버


```
로그인 api: /api/login?아디디랑패스워드
localhost:8080/api/login?id=user1&pw=1234
로그인에 성공하면 json토큰이 반환됨

발급받은 토큰을 요청메세지의 header에 담기
key: Authorization
value: Bearer jwt토큰 (Bearer+빈칸+토큰)
그리고 다른 요청 보내기
localhost:8080/board/

토큰이 유효하면 요청에 성공, 아니면 실패
```