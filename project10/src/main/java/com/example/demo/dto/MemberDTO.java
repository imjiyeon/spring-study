package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    String id; //아이디

    String password; //패스워드

    String name; //이름

    LocalDateTime regDate; //등록일

    LocalDateTime modDate; //수정일

    String role; //사용자 등급 추가 (사용자:ROLE_USER, 관리자:ROLE_ADMIN)

}
