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
        .antMatchers("/profile/users/**/edit").hasRole("ADMIN")
        .antMatchers("/profile/users/**").hasAnyRole("ADMIN", "MANAGER")
        .antMatchers("/profile/cars/**").hasAnyRole("ADMIN", "MANAGER")
        .antMatchers("/profile/orders/**/repair").hasAnyRole("ADMIN", "MANAGER")
        .antMatchers("/profile/users/**/cancellation").hasAnyRole("ADMIN", "MANAGER")
        .antMatchers("/profile/**").hasAnyRole("ADMIN", "MANAGER", "USER")
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
