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
	
	@Column(nullable = false, unique = true)
	private String nickname;
	
	@Column(nullable = false, unique = true)
	private String password;
	

public Amministratore(String nickname, String password) {
	this.nickname = nickname;
	this.password = password;
}

public Amministratore() {
	
}

//è da mettere qua o in una facade?
public boolean checkPassword(String pwd) {
	return this.password.equals(pwd);
}

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


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Amministratore other = (Amministratore) obj;
	if (id != other.id)
		return false;
	return true;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
	return result;
}
}