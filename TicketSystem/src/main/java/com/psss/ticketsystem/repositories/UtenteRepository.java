package com.psss.ticketsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.psss.ticketsystem.entities.Utente;


@Repository("utenteRepository")
public interface UtenteRepository extends CrudRepository<Utente, Integer>{
	
	public Utente findByUsername(String username);
}
