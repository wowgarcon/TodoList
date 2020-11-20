package com.wowgarcon.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity	//아레 작성하는 필터가 SpringSecurityFilterChain에 자동 등록
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.authorizeRequests()
		//.antMatchers("/todo/**").authenticated()
		//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		//.antMatchers("/").permitAll()
		//.anyRequest().permitAll();
		//.and()
		//.formLogin()
		//.loginPage("/login");
	}
	
}
