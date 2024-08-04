package com.example.demo.stats.dto;

import java.time.LocalDate;
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
public class StatsDTO {

    LocalDate orderDt; //주문일

    int count; //주문 건수
    
    int totalPrice; //주문 총금액

	LocalDateTime regDate; //등록일

	LocalDateTime modDate; //수정일

}
