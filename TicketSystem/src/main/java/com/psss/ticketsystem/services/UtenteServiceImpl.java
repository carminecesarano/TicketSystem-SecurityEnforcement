package com.psss.ticketsystem.services;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.psss.ticketsystem.entities.Utente;
import com.psss.ticketsystem.repositories.UtenteLdapRepository;
import com.psss.ticketsystem.repositories.UtenteRepository;

@Service("utenteService")
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private UtenteLdapRepository utenteLdapRepository;

	@Override
	public Utente save(Utente utente) {
		return utenteRepository.save(utente);
	}
	
	@Override
	public void saveLDAP(Utente utente) {
		utenteLdapRepository.create(utente);
	}
	
	@Override
	public void removeLDAP(Utente utente) {
		utenteLdapRepository.delete(utente);
	}
	
	@Override
	public Utente findByUsername(String username) {
		return utenteRepository.findByUsername(username);
	}
	
	@Override
	public List<Utente> findAllUsers() {
		return utenteRepository.findAll();
	}

}
