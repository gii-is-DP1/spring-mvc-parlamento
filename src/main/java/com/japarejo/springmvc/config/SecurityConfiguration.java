package com.japarejo.springmvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends  WebSecurityConfigurerAdapter {
	
	@Autowired	
	protected DataSource datasource;
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .authorizeRequests()	        
	        .anyRequest().authenticated()
	        .and()
	      .formLogin()	        
	        .permitAll()
	        .and()
	      .logout()
	        .permitAll();
	  }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("SELECT login,password,TRUE FROM USUARIO where login=?")
		.authoritiesByUsernameQuery("SELECT login,permiso FROM USUARIO where login=?");
	}
	
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
