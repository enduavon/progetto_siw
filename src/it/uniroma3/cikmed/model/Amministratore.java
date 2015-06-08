package it.uniroma3.cikmed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Amministratore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String email;	


	public Amministratore(String nickname, String password, String email) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
	}


	public Amministratore() {

	}

	public void checkPassword(String password) 
			throws Exception {
		if (!this.password.equals(password)) {
			throw new Exception();
		}
	}

	
	/*
	 * GETTERS & SETTERS
	 */	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}