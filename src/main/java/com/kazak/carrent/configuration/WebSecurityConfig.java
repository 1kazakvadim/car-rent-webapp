package com.kazak.carrent.configuration;

import com.kazak.carrent.service.impl.DatabaseUserDetailsServiceImpl;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final DatabaseUserDetailsServiceImpl databaseUserDetailsServiceImpl;
  private final DataSource dataSource;

  WebSecurityConfig(
      DatabaseUserDetailsServiceImpl databaseUserDetailsServiceImpl,
      DataSource dataSource) {
    this.databaseUserDetailsServiceImpl = databaseUserDetailsServiceImpl;
    this.dataSource = dataSource;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/profile/user/**").hasRole("ADMIN")
        .antMatchers("/profile/**").hasAnyRole("ADMIN","MANAGER","USER")
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .defaultSuccessUrl("/profile/information")
        .and()
        .logout()
        .logoutSuccessUrl("/index")
        .permitAll()
        .deleteCookies("JSESSIONID");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(
            "/css/**", "/fonts/**",
            "/images/**");
  }

}
