package com.example.demo.job;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.stats.entity.Stats;
import com.example.demo.stats.repository.StatsRepository;

@Configuration
public class SimpleJobConfig {

	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	PlatformTransactionManager manager;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	StatsRepository statsRepository;

	// JOB
	@Bean
	public Job simpleJob1() {
		return new JobBuilder("simpleJob", jobRepository)
				.start(step1())
				.next(step2())
				.next(step3())
				.build();
	}

	// STEP
	@Bean
	public Step step1() {
		return new StepBuilder("step1..", jobRepository)
				.tasklet(testTasklet(), manager).build();
	}

	// STEP
	@Bean
	public Step step2() {
		return new StepBuilder("step2..", jobRepository)
				.tasklet(test2Tasklet(), manager).build();
	}
	
	// STEP
	@Bean
	public Step step3() {
		return new StepBuilder("step3..", jobRepository)
				.tasklet(test3Tasklet(), manager).build();
	}

	// Tasklet: 스텝에서 하나의 작업만 처리하는 방식
	@Bean
	public Tasklet testTasklet() {
		return ((contribution, chunkContext) -> {
			
			System.out.println("Step1. 주문 건수와 금액 계산하기");
			
			// 주문 이력 가져오기
			List<Order> list = orderRepository.findAll();
			
//			LocalDate now = LocalDate.now();
			LocalDate now = LocalDate.of(2024, 10, 11);
			
			List<Order> filterList = list.stream().filter(entity -> {
				LocalDate orderDt = entity.getOrderDate();
				if (orderDt.equals(now)) {
					return true;
				} else {
					return false;
				}
			}).collect(Collectors.toList());
			
			for(Order order : filterList) {
				System.out.println(order);
			}
			
			// 전체 건수와 총금액 구하기
			long count = filterList.stream().count();
			int totalPrice = filterList.stream().mapToInt(dto->dto.getPrice()).sum();

			// 공유 데이터를 ExecutionContext에 추가
			// ExecutionContext: Job 실행 중에 데이터를 공유하기 위해 사용되는 저장소
			StepContext context = chunkContext.getStepContext(); 
			ExecutionContext executionContext = context.getStepExecution().getJobExecution().getExecutionContext();
			executionContext.put("count", count);
			executionContext.put("totalPrice", totalPrice);

			return RepeatStatus.FINISHED;
		});
	}

	@Bean
	public Tasklet test2Tasklet() {
		return ((contribution, chunkContext) -> {

			System.out.println("Step2. 통계테이블에 저장하기");
			
			// 공유 데이터 꺼내기
			StepContext context = chunkContext.getStepContext();
			ExecutionContext executionContext = context.getStepExecution().getJobExecution().getExecutionContext();
			Object count = executionContext.get("count");
			Object totalPrice = executionContext.get("totalPrice");
				
			// 집계 구하기
			int cnt = Integer.parseInt(count.toString());
			int total = Integer.parseInt(totalPrice.toString());
			
			System.out.println("총 건수:" + cnt);
			System.out.println("총 금액:" + total);
			
//			LocalDate now = LocalDate.now();
			LocalDate now = LocalDate.of(2024, 10, 11);
			
			// 집계 추가하기
			Stats stats = Stats.builder().orderDt(now).count(cnt).totalPrice(total).build();
			statsRepository.save(stats);
			
			return RepeatStatus.FINISHED;
		});
	}
	
	@Bean
	public Tasklet test3Tasklet() {
		return ((contribution, chunkContext) -> {

			System.out.println("Step3. 전날 주문 기록 삭제");

			List<Order> list = orderRepository.findAll();

			// 오늘 날짜
			LocalDate now = LocalDate.of(2024, 10, 11);
//			LocalDate now = LocalDate.now(); 
			// 어제 날짜
			LocalDate yesterday = now.minusDays(1);

			// 전날 들어온 주문이력을 찾아서 삭제
			list.stream().forEach(entity -> {
				int no = entity.getNo();
				LocalDate orderDate = entity.getOrderDate();

				if (orderDate.equals(yesterday)) {
					orderRepository.deleteById(no);
					System.out.println(no + " remove..");
				}
			});
			
			return RepeatStatus.FINISHED;
		});
	}

}

// 배치가 계속 실패하면, 테이블 삭제 후 재시도
