package com.example.demo.job;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SimpleJobConfig2 {

	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	PlatformTransactionManager manager;
		
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

	@Bean
	public Tasklet testTasklet() {
		return ((contribution, chunkContext) -> {
			
			System.out.println("Step1. " + LocalDateTime.now());
			
			return RepeatStatus.FINISHED;
		});
	}

	@Bean
	public Tasklet test2Tasklet() {
		return ((contribution, chunkContext) -> {

			System.out.println("Step2. " + LocalDateTime.now());
						
			return RepeatStatus.FINISHED;
		});
	}
	
	@Bean
	public Tasklet test3Tasklet() {
		return ((contribution, chunkContext) -> {

			System.out.println("Step3. " + LocalDateTime.now());
			
			return RepeatStatus.FINISHED;
		});
	}

}

// 배치가 계속 실패하면, 테이블 삭제 후 재시도
