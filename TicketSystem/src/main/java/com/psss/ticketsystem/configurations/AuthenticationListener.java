package com.psss.ticketsystem.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.psss.ticketsystem.entities.Utente;
import com.psss.ticketsystem.services.UtenteService;

@Component
public class AuthenticationListener implements ApplicationListener <AbstractAuthenticationEvent>
{

	@Autowired
	private UtenteService utenteService;
	
    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent appEvent)
    {       
      if (appEvent instanceof AuthenticationSuccessEvent) {
          AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
          String username = ((UserDetails)event.getAuthentication().getPrincipal()).getUsername();
          
          Utente utente = utenteService.findByUsername(username.toString());
          utente.setAttempts(0);
          utenteService.save(utente);
      }

      if (appEvent instanceof AuthenticationFailureBadCredentialsEvent) {
          AuthenticationFailureBadCredentialsEvent event = (AuthenticationFailureBadCredentialsEvent) appEvent;
          String username = event.getAuthentication().getPrincipal().toString();
          
          Utente utente = utenteService.findByUsername(username);
          
          if (utente != null) {
        	  System.out.println("Bad Credentials: password errata");
        	  
        	  utente.setAttempts(utente.getAttempts() + 1);
        	  if (utente.getAttempts() > 2) {
            	  utente.setStatus(0);
            	  utenteService.removeLDAP(utente);
              }
              
              utenteService.save(utente);
          }
          else {
        	  System.out.println("Bad Credentials: utente non esistente");
          }
         
      }
    }
}
