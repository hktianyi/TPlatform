package org.tplatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by tianyi on 2017/4/5.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    auth.authenticationProvider(daoAuthenticationProvider);//.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/static/**").permitAll()
        .antMatchers("/druid/**").access("hasRole('ADMIN') and hasRole('SYS')")
        .antMatchers("/**").hasRole("ADMIN" )
        .anyRequest().authenticated()
        .and().formLogin().loginPage( "/login").permitAll()
        .and().logout().permitAll();

    //CSRF
    http.csrf().disable();
    //iframe 同域验证
    http.headers().frameOptions().sameOrigin();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }
}
