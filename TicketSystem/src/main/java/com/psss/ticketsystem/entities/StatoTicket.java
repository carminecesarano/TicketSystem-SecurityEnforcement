package com.psss.ticketsystem.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statoticket")
public class StatoTicket implements java.io.Serializable {

	private static final long serialVersionUID = 1916428535658339233L;
	
	private Integer id;
	private String name;

	public StatoTicket() {
	}

	public StatoTicket(String name) {
		this.name = name;
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

	@Column(name = "name", nullable = false, length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void aggiornaStato(Ticket ticket, Utente operatore) {	
	}
	
	public Notifica creaNotifica(Ticket ticket) {
		return new Notifica();
	}

}
