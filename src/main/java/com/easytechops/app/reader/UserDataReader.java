package com.easytechops.app.reader;

import com.easytechops.app.model.User;
import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * Created by TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com> on 12/1/24
 *
 * @author : TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com>
 * @date : 12/1/24
 */
@Configuration
public class UserDataReader {

  @Bean
  public JdbcCursorItemReader<User> reader(
      @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
    return new JdbcCursorItemReaderBuilder<User>()
        .name("userDataReader")
        .dataSource(mysqlDataSource)
        .sql("SELECT * FROM user")
        .rowMapper(new BeanPropertyRowMapper<>(User.class))
        .build();
  }
}
