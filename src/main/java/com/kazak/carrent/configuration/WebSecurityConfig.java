package com.kazak.carrent.configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String ROLE_ADMIN = "ADMIN";
  private static final String ROLE_MANAGER = "MANAGER";
  private static final String ROLE_USER = "USER";

  private final DataSource dataSource;

  WebSecurityConfig(DataSource dataSource) {
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
        .antMatchers("/profile/users/**/edit").hasRole(ROLE_ADMIN)
        .antMatchers("/profile/users/**").hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
        .antMatchers("/profile/cars/**").hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
        .antMatchers("/profile/orders/**/repair").hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
        .antMatchers("/profile/orders/**/cancellation").hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
        .antMatchers("/profile/**").hasAnyRole(ROLE_ADMIN, ROLE_MANAGER, ROLE_USER)
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .defaultSuccessUrl("/profile")
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
