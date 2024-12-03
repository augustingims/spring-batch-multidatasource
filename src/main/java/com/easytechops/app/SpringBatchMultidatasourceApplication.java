package com.easytechops.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBatchMultidatasourceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBatchMultidatasourceApplication.class, args);
  }

//  @Bean
//  CommandLineRunner run(JobLauncher jobLauncher, Job userJob) {
//    return args -> {
//      JobParameters params =
//          new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
//      jobLauncher.run(userJob, params);
//    };
//  }
}
