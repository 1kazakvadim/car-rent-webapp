package com.kazak.carrent.configuration;

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
    dataSourceBuilder.url("jdbc:mysql://localhost:3306/car_rent");
    dataSourceBuilder.username("root");
    dataSourceBuilder.password("root");
    return dataSourceBuilder.build();
  }

}
