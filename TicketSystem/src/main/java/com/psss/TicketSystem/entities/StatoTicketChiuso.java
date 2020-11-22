package com.psss.TicketSystem.entities;

public class StatoTicketChiuso extends StatoTicket{

	public StatoTicketChiuso() {
		this.setId(3);
		this.setName("CHIUSO");
	}
	
	@Override
	public void aggiornaStato(Ticket ticket, Utente operatore) {
		
	}

	@Override
	public Notifica creaNotifica(Ticket ticket) {
		String messaggio = "Il ticket no. " + ticket.getId() + " è stato chiuso.";
		return new Notifica(ticket, messaggio, false);
	}
}
