package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// 배치와 스케줄러 연결

@Component
public class BatchScheduler {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job simpleJob1;  // simpleJob1을 주입

	// 매 10초마다 Job 실행
	@Scheduled(cron = "0/10 * * * * *")
	public void runBatchJob() throws Exception {
		
		// Job의 실행 이력을 관리하기 위해 현재시간을 파라미터로 전달
		jobLauncher.run(simpleJob1, new JobParametersBuilder()
						            .addLong("time", System.currentTimeMillis())
						            .toJobParameters());
	}
	
}
