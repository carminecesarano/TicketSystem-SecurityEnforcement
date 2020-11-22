package com.psss.TicketSystem.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.psss.TicketSystem.entities.Utente;


public interface UtenteService extends UserDetailsService {
	
	public Iterable<Utente> findAll();
	
	public Utente findByUsername(String username);

	public Utente find(int id);
}
