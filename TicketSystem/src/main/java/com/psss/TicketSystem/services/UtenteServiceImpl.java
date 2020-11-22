package com.psss.TicketSystem.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.psss.TicketSystem.entities.Utente;
import com.psss.TicketSystem.repositories.UtenteRepository;

@Service("utenteService")
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Utente utente = utenteRepository.findByUsername(username);
		if(utente == null) {
			throw new UsernameNotFoundException("Username non trovato per " + username);
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		System.out.println(utente.getRuolo().getName());
		grantedAuthorities.add(new SimpleGrantedAuthority(utente.getRuolo().getName()));
		return new User(utente.getUsername(), utente.getPassword(), grantedAuthorities);
	}

	@Override
	public Utente findByUsername(String username) {
		return utenteRepository.findByUsername(username);
	}

	@Override
	public Iterable<Utente> findAll() {
		return utenteRepository.findAll();
	}

	@Override
	public Utente find(int id) {
		return utenteRepository.findById(id).get();
	}
}
