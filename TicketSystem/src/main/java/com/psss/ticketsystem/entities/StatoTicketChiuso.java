package com.psss.ticketsystem.entities;

public class StatoTicketChiuso extends StatoTicket{

	private static final long serialVersionUID = 8108116044211428778L;

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
