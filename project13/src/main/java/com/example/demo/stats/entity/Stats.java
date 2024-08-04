package com.example.demo.stats.entity;

import java.time.LocalDate;

import com.example.demo.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="tbl_stats")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stats extends BaseEntity {

    @Id
    LocalDate orderDt; //주문일

    int count; //주문 건수
    
    int totalPrice; //주문 총금액

}
