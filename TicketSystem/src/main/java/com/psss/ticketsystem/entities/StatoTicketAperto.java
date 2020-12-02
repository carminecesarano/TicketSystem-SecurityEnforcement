package com.psss.ticketsystem.entities;

public class StatoTicketAperto extends StatoTicket {
	
	public StatoTicketAperto() {
		this.setId(1);
		this.setName("APERTO");
	}
	
	@Override
	public void aggiornaStato(Ticket ticket, Utente operatore) {
		StatoTicketInLavorazione stato = new StatoTicketInLavorazione();
		ticket.setStatoTicket(stato);
		ticket.setOperatore(operatore);
	}
	
	@Override
	public Notifica creaNotifica(Ticket ticket) {
		return new Notifica();
	}

}
