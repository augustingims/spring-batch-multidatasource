package com.easytechops.app.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com> on 12/3/24
 *
 * @author : TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com>
 * @date : 12/3/24
 */
@Component
public class BatchScheduler {
  private final JobLauncher jobLauncher;
  private final Job userJob;

  public BatchScheduler(JobLauncher jobLauncher, Job userJob) {
    this.jobLauncher = jobLauncher;
    this.userJob = userJob;
  }

  @Scheduled(cron = "0 */1 * * * *")
  public void runJob() {
    try {
      JobParameters params =
          new JobParametersBuilder()
              .addLong(
                  "time",
                  System.currentTimeMillis()) // Paramètre unique pour éviter les duplications
              .toJobParameters();

      jobLauncher.run(userJob, params);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
