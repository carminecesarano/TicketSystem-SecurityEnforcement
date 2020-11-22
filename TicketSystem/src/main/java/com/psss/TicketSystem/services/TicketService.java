package com.psss.TicketSystem.services;

import java.util.List;


import com.psss.TicketSystem.entities.Ticket;

public interface TicketService {
	
	public Ticket save(Ticket ticket);
		
	public List<Ticket> cercaTicketCliente(String username);
	
	public List<Ticket> cercaTicketOperatore(String username);
	
	public List<Ticket> cercaTicketStatoAperto();
	
	public List<Ticket> findAllTicket();
	
	public Ticket findTicket(int id);

}
