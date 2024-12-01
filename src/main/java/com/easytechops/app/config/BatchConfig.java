package com.easytechops.app.config;

import com.easytechops.app.model.User;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com> on 12/1/24
 *
 * @author : TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com>
 * @date : 12/1/24
 */
@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
public class BatchConfig {

  private final PlatformTransactionManager transactionManager;
  private final DataSource dataSource;

  public BatchConfig(
      PlatformTransactionManager transactionManager,
      @Qualifier("dataSource") DataSource dataSource) {
    this.transactionManager = transactionManager;
    this.dataSource = dataSource;
  }

  @Bean
  public JobRepository jobRepository() {
    JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
    factory.setDataSource(dataSource);
    factory.setTransactionManager(transactionManager);
    factory.setDatabaseType("H2");
    factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
    factory.setTablePrefix("BATCH_"); // Préfixe personnalisé pour les tables Spring Batch
    try {
      factory.afterPropertiesSet();
      return factory.getObject();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @Bean
  public Job userJob(JobRepository jobRepository, Step userStep) {
    return new JobBuilder("userJob", jobRepository).start(userStep).build();
  }

  @Bean
  public Step userStep(
      JobRepository jobRepository,
      JdbcCursorItemReader<User> reader,
      ItemProcessor<User, User> processor,
      FlatFileItemWriter<User> writer) {
    return new StepBuilder("userStep", jobRepository)
        .<User, User>chunk(10, transactionManager)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
  }
}
