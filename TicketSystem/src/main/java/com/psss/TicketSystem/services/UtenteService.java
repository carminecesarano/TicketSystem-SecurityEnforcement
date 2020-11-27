package com.psss.TicketSystem.services;

import com.psss.TicketSystem.entities.Utente;


public interface UtenteService {
	
	public Iterable<Utente> findAll();
	
	public Utente findByUsername(String username);

	public Utente find(int id);
}
