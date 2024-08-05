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
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.service.OrderService;
import com.example.demo.stats.dto.StatsDTO;
import com.example.demo.stats.service.StatsService;

@Configuration
public class SimpleJobConfig {

	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	PlatformTransactionManager platformTransactionManager;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	StatsService statsService;

	// JOB
	@Bean
	public Job simpleJob1() {
		return new JobBuilder("simpleJob", jobRepository)
				.start(step1())
				.next(step2())
				.build();
	}

	// STEP
	@Bean
	public Step step1() {
		return new StepBuilder("step1..", jobRepository)
				.tasklet(testTasklet(), platformTransactionManager).build();
	}

	// STEP
	@Bean
	public Step step2() {
		return new StepBuilder("step2..", jobRepository)
				.tasklet(test2Tasklet(), platformTransactionManager).build();
	}

	// Tasklet: 스텝에서 하나의 작업만 처리하는 방식
	@Bean
	public Tasklet testTasklet() {
		return ((contribution, chunkContext) -> {
			
			System.out.println(">>>>> This is Step1");
			
			// 주문 이력 가져오기
			List<OrderDTO> list = orderService.getList();
			
			List<OrderDTO> filterList = list.stream()
						.filter(dto -> dto.getRegDate().toLocalDate().equals(LocalDate.now()))
						.collect(Collectors.toList());
			
			for(OrderDTO dto: filterList) {
				System.out.println(dto);
			}
			
			// 전체 건수와 총금액 구하기
			long count = filterList.stream().count();
			int totalPrice = filterList.stream().mapToInt(dto->dto.getPrice()).sum();

			// 공유 데이터 추가
			StepContext context = chunkContext.getStepContext();
		    context.getStepExecution().getJobExecution().getExecutionContext().put("count", count);
		    context.getStepExecution().getJobExecution().getExecutionContext().put("totalPrice", totalPrice);

			return RepeatStatus.FINISHED;
		});
	}

	@Bean
	public Tasklet test2Tasklet() {
		return ((contribution, chunkContext) -> {

			System.out.println(">>>>> This is Step2");
			
			// 공유 데이터 꺼내기
			StepContext context = chunkContext.getStepContext();
			Object count = context.getStepExecution().getJobExecution().getExecutionContext().get("count");
			Object totalPrice = context.getStepExecution().getJobExecution().getExecutionContext().get("totalPrice");
				
			// 집계 구하기
			int cnt = Integer.parseInt(count.toString());
			int total = Integer.parseInt(totalPrice.toString());
			
			System.out.println("총 건수:" + cnt);
			System.out.println("총 금액:" + total);
			
			// 집계 추가하기
			StatsDTO dto = StatsDTO.builder().orderDt(LocalDate.now()).count(cnt).totalPrice(total).build();
			statsService.register(dto);
			
			return RepeatStatus.FINISHED;
		});
	}

}

// 배치가 계속 실패하면, 테이블 삭제 후 재시도
