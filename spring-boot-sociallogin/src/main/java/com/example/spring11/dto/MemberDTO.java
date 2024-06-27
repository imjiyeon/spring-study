package com.example.spring11.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    String id;
    String password;
    String name;
    LocalDateTime regDate;
    LocalDateTime modDate;
    String role;
    boolean fromSocial; // 소셜 회원 여부 추가

}
