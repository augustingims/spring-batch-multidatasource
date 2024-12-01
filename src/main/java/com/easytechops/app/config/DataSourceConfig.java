package com.easytechops.app.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com> on 12/2/24
 *
 * @author : TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com>
 * @date : 12/2/24
 */
@Configuration
public class DataSourceConfig {

  public static final String SPRING_DATASOURCE_MYSQL = "spring.datasource.mysql";

  @Bean
  @ConfigurationProperties(prefix = SPRING_DATASOURCE_MYSQL)
  public DataSourceProperties mysqlDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean(name = "mysqlDataSource")
  @ConfigurationProperties(prefix = SPRING_DATASOURCE_MYSQL)
  public DataSource mysqlDataSource() {
    return mysqlDataSourceProperties().initializeDataSourceBuilder().build();
  }

  @Primary
  @Bean(name = "dataSource")
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder
        .addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
        .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
        .setType(EmbeddedDatabaseType.H2)
        .setName("batch_db")
        .build();
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }
}
