package com.psss.TicketSystem.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ticket")
public class Ticket implements java.io.Serializable {

	private Integer id;
	private Utente operatore;
	private Utente cliente;
	private String title;
	private String description;
	private Date createdDate;
	private StatoTicket statoTicket;

	public Ticket() {
	}

	public Ticket(Utente operatore, Utente cliente, int status, String title, String description, Date createdDate, StatoTicket statoTicket) {
		this.operatore = operatore;
		this.cliente = cliente;
		this.title = title;
		this.description = description;
		this.createdDate = createdDate;
		this.statoTicket = statoTicket;
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
	@JoinColumn(name = "operatore_id", nullable = false)
	public Utente getOperatore() {
		return this.operatore;
	}

	public void setOperatore(Utente operatore) {
		this.operatore = operatore;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	public Utente getCliente() {
		return this.cliente;
	}

	public void setCliente(Utente cliente) {
		this.cliente = cliente;
	}

	@Column(name = "title", nullable = false, length = 250)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", nullable = false, length = 0)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status")
	public StatoTicket getStatoTicket() {
		return this.statoTicket;
	}

	public void setStatoTicket(StatoTicket statoticket) {
		this.statoTicket = statoticket;
	}
	
	public void aggiornaStatoTicket(Ticket ticket, Utente operatore) {
		this.statoTicket.aggiornaStato(ticket, operatore);
	}
	
	public Notifica creaNotifica(Ticket ticket) {
		return this.statoTicket.creaNotifica(ticket);
	}
	



}
