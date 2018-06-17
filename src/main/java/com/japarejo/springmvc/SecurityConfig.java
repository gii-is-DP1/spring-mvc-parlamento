package com.japarejo.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.authentication.event.LoggerListener;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
			.authorizeRequests()
				.antMatchers("/","/index.html","/login.html","/webjars/jquery/**","/webjars/bootstrap/**").permitAll()
				.antMatchers("/api/**").permitAll()
				.antMatchers("/organos*", "/parlamentarios*").authenticated()
				.anyRequest().authenticated().and()  
			.formLogin()
				.defaultSuccessUrl("/organos")
				.failureUrl("/login.html?error=true")
				.permitAll()
            .and()
            .logout()
            	.permitAll();
	}

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("root").roles("ADMIN");
    }
	
	 @Bean
	 public LoggerListener loggerListener(){
		 return new LoggerListener();
	 }
}
