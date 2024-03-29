package com.indraphan.learn.springcloud.microservice.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.indraphan.learn.springcloud.microservice.users.service.UsersService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment env;
	private UsersService usersService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public WebSecurity(Environment env, UsersService usersService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.env = env;
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers(env.getProperty("api.h2console.url.path")).permitAll()
			.antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"))
			.and().addFilter(getAuthenticationFilter());
		
		http.headers().frameOptions().disable();
	}
	
	
	/**
	 * To get AuthenticationFilter
	 * 
	 * @return AuthenticationFilter
	 * @throws Exception
	 */
	private AuthenticationFilter getAuthenticationFilter() throws Exception{
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(usersService, env, authenticationManager());
		
		//customize user authentication URL
		authenticationFilter.setFilterProcessesUrl(env.getProperty("login.url.path"));
		
		return authenticationFilter;
	}
	
	/**
	 * Configure method to define implementation of UserDetailsService and Password encoder used
	 * 
	 * @param auth		AuthenticationManagerBuilder
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
	}
}
