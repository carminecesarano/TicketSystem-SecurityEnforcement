package com.psss.ticketsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psss.ticketsystem.entities.Ticket;
import com.psss.ticketsystem.entities.Utente;
import com.psss.ticketsystem.repositories.TicketRepository;
import com.psss.ticketsystem.repositories.UtenteRepository;


@Service("ticketService")
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UtenteRepository accountRepository;
	
	
	@Override
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
	@Override
	public List<Ticket> cercaAllTicketCliente(String username) {
		Utente account = accountRepository.findByUsername(username);
		return ticketRepository.cercaAllTicketCliente(account.getId());
	}
	
	@Override
	public Ticket cercaTicketCliente(String username, int idTicket) {
		Utente account = accountRepository.findByUsername(username);
		return ticketRepository.cercaTicketCliente(account.getId(), idTicket);
	}
	
	@Override
	public List<Ticket> cercaTicketOperatore(String username) {
		Utente account = accountRepository.findByUsername(username);
		return ticketRepository.cercaTicketOperatore(account.getId());
	}
	
	@Override
	public List<Ticket> cercaTicketStatoAperto() {
		int statoAperto = 1;
		return ticketRepository.cercaTicketStato(statoAperto);
	}
	
	@Override
	public List<Ticket> findAllTicket() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket findTicket(int id) {
		return ticketRepository.findById(id).get();
	}


}
