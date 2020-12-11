package com.psss.ticketsystem.services;

import com.psss.ticketsystem.entities.Utente;
import java.util.List;

public interface UtenteService {
	
	public Utente save(Utente utente);
	
	public void saveLDAP(Utente utente);
	
	public void removeLDAP(Utente utente);
	
	public Utente findByUsername(String username);

	public List<Utente> findAllUsers();
}
