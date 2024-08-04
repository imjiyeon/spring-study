package com.example.demo.order.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    int no; //제품번호

    String productName; //제품명

    int price; //가격

	LocalDateTime regDate; //등록일

	LocalDateTime modDate; //수정일

}
