package com.easytechops.app;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBatchMultidatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchMultidatasourceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(JobLauncher jobLauncher, Job userJob) {
		return args -> {
			JobParameters params = new JobParametersBuilder()
					.addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(userJob, params);
		};
	}
}
