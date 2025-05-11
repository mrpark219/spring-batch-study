package me.park.springbatchstudy.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {

	private final SimpleJobParameter simpleJobParameter;

	@Bean
	public Job simpleJob(JobRepository jobRepository, Step simpleStep1) {
		return new JobBuilder("simpleJob", jobRepository)
			.start(simpleStep1)
			.build();
	}

	@Bean
	public Step simpleStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("simpleStep1", jobRepository)
			.tasklet((contribution, chunkContext) -> {
				log.info("Simple Job - Step 1 executed");
				log.info("Request Date: {}", simpleJobParameter.getRequestDate());
				return RepeatStatus.FINISHED;
			}, transactionManager)
			.build();
	}

	@JobScope
	@Bean
	public SimpleJobParameter simpleJobParameter() {
		return new SimpleJobParameter();
	}

}