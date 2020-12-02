package com.psss.ticketsystem.services;

import com.psss.ticketsystem.entities.Utente;


public interface UtenteService {
	
	public Utente findByUsername(String username);

}
