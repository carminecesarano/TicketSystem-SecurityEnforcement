package com.psss.ticketsystem.configurations;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;
    
    @Value("${ldap.user.dn.pattern}")
    private String userDnPatter;
    
    @Value("${ldap.username}")
    private String ldapSecurityPrincipal;

    @Value("${ldap.password}")
    private String ldapPrincipalPassword;
    
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
			        .authorizeRequests()
			        
			        .antMatchers("/").permitAll()
			        .antMatchers("/login-panel/login").permitAll()
			        .antMatchers("/resources/public/**").permitAll()
			        .antMatchers("/login-panel/accessDenied").authenticated()
			        .antMatchers("/login-panel/welcome").authenticated()
			        .antMatchers("/dashboard/**").authenticated()
					.antMatchers("/ticket/details/*").authenticated()
					.antMatchers("/ticket/send").access("hasAuthority('ROLE_CLIENTI')")
					.antMatchers("/ticket/history_cliente").access("hasAuthority('ROLE_CLIENTI')")
					.antMatchers("/ticket/history_operatore").access("hasAuthority('ROLE_OPERATORI')")
					.antMatchers("/ticket/history_aperti").access("hasAuthority('ROLE_OPERATORI')")
					.antMatchers("/ticket/aggiorna_stato/*").access("hasAuthority('ROLE_OPERATORI')")
					.anyRequest().denyAll()
					
			        .and()
			        .formLogin()
			        .loginPage("/login-panel/login")
					.loginProcessingUrl("/login/process-login")
					.defaultSuccessUrl("/login-panel/welcome")
					.failureUrl("/login-panel/login?error")
					.and()
					.logout()
					.logoutUrl("/process-logout")
					.logoutSuccessUrl("/login-panel/login?logout")
					.deleteCookies("JSESSIONID")
					.and()
					.exceptionHandling().accessDeniedPage("/login-panel/accessDenied");			
	
		httpSecurity
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
					.invalidSessionUrl("/login-panel/login?expired");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
	        .ldapAuthentication()
	        .userDnPatterns(userDnPatter)
	        .groupSearchBase("ou=groups")
	        .contextSource()
	        .url(ldapUrl + ldapBaseDn)
	        .managerDn(ldapSecurityPrincipal)				// DN of the user who will bind to the LDAP server to perform the search
	        .managerPassword(ldapPrincipalPassword)			// Password of the user who will bind to the LDAP server to perform the search
	        .and()
	        .passwordCompare()
	        .passwordEncoder(new BCryptPasswordEncoder())
	        .passwordAttribute("userPassword");
	}
	
	@Configuration
	public class MyHttpSessionListener implements HttpSessionListener {
	    @Override
	    public void sessionCreated(HttpSessionEvent event) {
	        event.getSession().setMaxInactiveInterval(600);					// 10 minutes
	    }
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	private PasswordEncoder passwordEncoder() {
		final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return bcrypt.encode(rawPassword.toString());
			}
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return bcrypt.matches(rawPassword, encodedPassword);
			}
		};
	}

	@Bean
	public BCryptPasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();
	}
		
}

