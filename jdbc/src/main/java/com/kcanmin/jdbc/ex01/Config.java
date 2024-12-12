package com.kcanmin.jdbc.ex01;

// import org.mariadb.jdbc.Connection;

// import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.Data;

@Configuration
@Data
public class Config {
  // @Autowired
  // public Connection connection;

  // Connection connection;

  @Autowired
  private HikariConfig hikariConfig;
  
  @Autowired
  private HikariDataSource hikariDataSource;

  @Autowired
  private JdbcTemplate jdbcTemplate;
}
