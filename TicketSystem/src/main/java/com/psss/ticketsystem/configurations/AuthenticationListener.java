package com.psss.ticketsystem.configurations;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationListener implements ApplicationListener <AbstractAuthenticationEvent>
{

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent appEvent)
    {       
        if (appEvent instanceof AuthenticationSuccessEvent)
      {
          AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
          // add code here to handle successful login event
          // System.out.println("SUCCESSFUL LOGIN");
      }

      if (appEvent instanceof AuthenticationFailureBadCredentialsEvent)
      {
          AuthenticationFailureBadCredentialsEvent event = (AuthenticationFailureBadCredentialsEvent) appEvent;

          // add code here to handle unsuccessful login event
          // for example, counting the number of login failure attempts and storing it in db
          // this count can be used to lock or disable any user account as per business requirements
          
          Object userName = event.getAuthentication().getPrincipal();
          Object credentials = event.getAuthentication().getCredentials();
          //System.out.println("Failed login using USERNAME [" + userName + "]");
          //System.out.println("Failed login using PASSWORD [" + credentials + "]");
                   
          //System.out.println("UNSUCCESSFUL LOGIN");
      }
    }
}
