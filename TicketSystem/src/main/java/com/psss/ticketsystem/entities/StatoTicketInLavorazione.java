package com.psss.ticketsystem.entities;

public class StatoTicketInLavorazione extends StatoTicket{

	public StatoTicketInLavorazione() {
		this.setId(2);
		this.setName("IN_LAVORAZIONE");
	}
	
	@Override
	public void aggiornaStato(Ticket ticket, Utente operatore) {
		StatoTicketChiuso stato = new StatoTicketChiuso();
		ticket.setStatoTicket(stato);
	}
	
	@Override
	public Notifica creaNotifica(Ticket ticket) {
		String messaggio = "Il ticket no. " + ticket.getId() + " è stato preso in carico.";
		return new Notifica(ticket, messaggio, false);
	}
}
