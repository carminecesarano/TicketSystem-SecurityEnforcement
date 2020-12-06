package com.psss.ticketsystem.entities;

public class StatoTicketAperto extends StatoTicket {
	
	private static final long serialVersionUID = -1366864074807870746L;

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
