package com.psss.TicketSystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notifiche")
public class Notifica implements java.io.Serializable {

	private Integer id;
	private String message;
	private boolean letta;
	private Ticket ticket;

	public Notifica() {
	}
	
	public Notifica(String content) {
		this.message = content;
	}

	public Notifica(Ticket ticket, String messaggio, boolean letta) {
		this.ticket = ticket;
		this.message = messaggio;
		this.letta = letta;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ticket", nullable = false)
	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Column(name = "messaggio", nullable = false, length = 250)
	public String getContent() {
		return this.message;
	}

	public void setContent(String messaggio) {
		this.message = messaggio;
	}

	@Column(name = "letta", nullable = false)
	public boolean isLetta() {
		return this.letta;
	}

	public void setLetta(boolean letta) {
		this.letta = letta;
	}
	

}
