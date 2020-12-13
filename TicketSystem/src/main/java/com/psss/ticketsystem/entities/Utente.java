package com.psss.ticketsystem.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Date;
import com.psss.ticketsystem.utils.TransitConverterUser;
import com.psss.ticketsystem.annotations.Password;

@Entity
@Table(name = "utenti")
public class Utente implements java.io.Serializable {

	private static final long serialVersionUID = -6999018310754628005L;
	
	private Integer id;
	
	@NotNull
	@Size(min=3, max=15)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String username;
	
	@NotNull
	@Size(min=5, max=35)
	@Pattern(regexp = "^[a-zA-Z\\u0020]*$")
	private String fullName;
	
	@Email
	private String email;
	
	@NotNull
	@Size(min=7)
	@Digits(integer=12, fraction=0)
	private String phone;
	
	@NotNull
	@Password
	private String password = "Password.98";
	
	private String role;
	
	private Date lastLogin;
	
	private int status;
	
	private int attempts;
	
	public Utente() {
	}

	public Utente(String username, String fullName, String email, String phone, String password, String role, Date lastLogin, int status) {
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.lastLogin = lastLogin;
		this.status = status;
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
	
	@Transient
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "role", nullable = false)
//	@Convert(converter = TransitConverterUser.class)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "lastlogin", nullable = false, length = 0)
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "status", nullable = false, length = 1)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "attempts", nullable = false, length = 1)
	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
}
