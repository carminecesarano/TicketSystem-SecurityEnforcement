package com.psss.ticketsystem.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.DefaultTlsDirContextAuthenticationStrategy;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

@EnableWebSecurity
@Configuration
public class LDAPAuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;
    
    @Value("${ldap.user.dn.pattern}")
    private String userDnPattern;
    
    @Autowired
	private VaultTemplate vaultTemplate;
	
	@Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
		
        auth.ldapAuthentication()
            .userDnPatterns(userDnPattern)
	        .groupSearchBase("ou=groups")
            .contextSource(contextSource())
	        .passwordCompare()
	        .passwordEncoder(new BCryptPasswordEncoder())
	        .passwordAttribute("userPassword");
    }


    @Bean
    public LdapContextSource contextSource() {

    	VaultResponse response = vaultTemplate.read("/openldap/static-cred/ssdgroup");
    	
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(ldapUrl);
        contextSource.setBase(ldapBaseDn);
        contextSource.setUserDn(response.getData().get("dn").toString());
        contextSource.setPassword(response.getData().get("password").toString());
        contextSource.setAuthenticationStrategy(new DefaultTlsDirContextAuthenticationStrategy());

        return contextSource;
    }
}
