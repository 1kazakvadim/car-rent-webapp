package com.kazak.carrent.model.configuration;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

  @Bean
  public DataSource getDataSource(){
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
    dataSourceBuilder.url("jdbc:mysql://localhost:3306/webapp");
    dataSourceBuilder.username("root");
    dataSourceBuilder.password("root");
    return dataSourceBuilder.build();
  }

}
