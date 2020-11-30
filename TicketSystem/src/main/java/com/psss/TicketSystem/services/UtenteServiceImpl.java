package com.psss.TicketSystem.services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.psss.TicketSystem.entities.Utente;
import com.psss.TicketSystem.repositories.UtenteRepository;

@Service("utenteService")
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public Utente findByUsername(String username) {
		return utenteRepository.findByUsername(username);
	}

}
