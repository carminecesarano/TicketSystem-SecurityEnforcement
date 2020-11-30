package com.psss.TicketSystem.services;

import com.psss.TicketSystem.entities.Utente;


public interface UtenteService {
	
	public Utente findByUsername(String username);

}
