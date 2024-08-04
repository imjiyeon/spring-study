package com.example.demo.stats.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.stats.entity.Stats;

public interface StatsRepository extends JpaRepository<Stats, LocalDate> {

}
