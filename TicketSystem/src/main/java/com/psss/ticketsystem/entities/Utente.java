package com.psss.ticketsystem.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.psss.ticketsystem.utils.TransitConverterUser;

@Entity
@Table(name = "utenti")
public class Utente implements java.io.Serializable {

	private static final long serialVersionUID = -6999018310754628005L;
	
	private Integer id;
	private String username;
	private String fullName;
	private String email;
	private String phone;
	
	public Utente() {
	}

	public Utente(String username, String fullName, String email, String phone) {
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
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

	
	@Column(name = "username", nullable = false, length = 250)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Convert(converter = TransitConverterUser.class)
	@Column(name = "full_name", nullable = false)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Convert(converter = TransitConverterUser.class)
	@Column(name = "email", nullable = false, length = 250)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Convert(converter = TransitConverterUser.class)
	@Column(name = "phone", nullable = false, length = 250)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
