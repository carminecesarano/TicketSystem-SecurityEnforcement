package com.psss.ticketsystem.configurations;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
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
    private String userDnPattern;

    @Autowired
	private VaultTemplate vaultTemplate;
    
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
			        .authorizeRequests()
			        
			        .mvcMatchers("/").permitAll()
			        .mvcMatchers("/login-panel/login").permitAll()
			        .mvcMatchers("/resources/public/**").permitAll()
			        .mvcMatchers("/resources/private/**").denyAll()
			        .mvcMatchers("/login-panel/accessDenied").authenticated()
			        .mvcMatchers("/login-panel/welcome").authenticated()
			        .mvcMatchers("/dashboard/**").authenticated()
					.mvcMatchers("/ticket/details/*").authenticated()
					.mvcMatchers("/ticket/send").access("hasAuthority('ROLE_CLIENTI')")
					.mvcMatchers("/ticket/history_cliente").access("hasAuthority('ROLE_CLIENTI')")
					.mvcMatchers("/ticket/history_operatore").access("hasAuthority('ROLE_OPERATORI')")
					.mvcMatchers("/ticket/history_aperti").access("hasAuthority('ROLE_OPERATORI')")
					.mvcMatchers("/ticket/aggiorna_stato/*").access("hasAuthority('ROLE_OPERATORI')")
					.mvcMatchers("/user/queue/notify").access("hasAuthority('ROLE_OPERATORI')")
			        .mvcMatchers("/ws/**").authenticated()
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
		
		VaultResponse response = vaultTemplate.read("/openldap/static-cred/ssdgroup");
			
		auth
	        .ldapAuthentication()
	        .userDnPatterns(userDnPattern)
	        .groupSearchBase("ou=groups")
	        .contextSource()
	        .url(ldapUrl + ldapBaseDn)	
	        .managerDn(response.getData().get("dn").toString())						// DN of the user who will bind to the LDAP server to perform the search
	        .managerPassword(response.getData().get("password").toString())			// Password of the user who will bind to the LDAP server to perform the search
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
	
	
		
}

