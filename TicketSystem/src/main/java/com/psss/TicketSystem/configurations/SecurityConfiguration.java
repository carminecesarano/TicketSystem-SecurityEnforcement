package com.psss.TicketSystem.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.psss.TicketSystem.services.UtenteService;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UtenteService utenteService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable();
		
		httpSecurity.authorizeRequests()
					.antMatchers("/dashboard/**").access("hasRole('ROLE_CLIENTE') or hasRole('ROLE_OPERATORE')")
					.antMatchers("/ticket/details").access("hasRole('ROLE_CLIENTE') or hasRole('ROLE_OPERATORE')")
					
					.antMatchers("/ticket/send").access("hasRole('ROLE_CLIENTE')")
					.antMatchers("/ticket/history_cliente").access("hasRole('ROLE_CLIENTE')")
					
					.antMatchers("ticket/history_operatore").access("hasRole('ROLE_OPERATORE')")
					.antMatchers("ticket/history_aperti").access("hasRole('ROLE_OPERATORE')")
					
					.and()
					.formLogin()
					.loginPage("/login-panel")
					.loginProcessingUrl("/login/process-login")
					.defaultSuccessUrl("/login-panel/welcome")
					.failureUrl("/login-panel/login?error")
					.usernameParameter("username").passwordParameter("password")
					.and()
					.logout()
					.logoutUrl("/process-logout")
					.logoutSuccessUrl("/login-panel/login?logout")
					.deleteCookies("JSESSIONID")
					.and()
					.exceptionHandling().accessDeniedPage("/login-panel/accessDenied");			
					
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(utenteService);
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	
	
}

