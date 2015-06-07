package it.uniroma3.cikmed.model;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Cliente {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String password;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(nullable = false)
	@Temporal(value=TemporalType.DATE)
	private Date dataDiNascita;

	@Column(nullable = true)
	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar dataDiRegistrazione;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	private Indirizzo indirizzo;
	
	@OneToMany(mappedBy = "cliente")
	private List<Ordine> ordini;


	public Cliente(){

	}
	
	public Cliente(String nome, String nickname, String password, String cognome, 
			Date dataDiNascita, String email) {
		
		this.nickname = nickname;
		this.password = password;
		this.cognome = cognome;
		this.nome = nome;
		this.dataDiNascita = dataDiNascita;
		this.email = email;
		this.dataDiRegistrazione = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		this.ordini = new ArrayList<Ordine>();
	}
	
	
	public void checkPassword(String password) throws Exception {
		if (!this.password.equals(password)) {
		System.out.println("il cliente non esiste");	
			throw new Exception();
		}
	}
	
	public void addOrdine (Ordine o) {
		this.ordini.add(o);
	}
	
	
	/*
	 * GETTERS & SETTERS
	 */
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Indirizzo getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(Indirizzo ind) {
		this.indirizzo = ind;
	}

	public Date getDataDiNascita() {
		return this.dataDiNascita;
	}

	public void setDataDiNascita(Date data) {
		this.dataDiNascita = data;
	}

	public Calendar getDataDiRegistrazione() {
		return dataDiRegistrazione;
	}

	public void setDataDiRegistrazione(Calendar data) {
		this.dataDiRegistrazione = data;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cogn) {
		this.cognome = cogn;
	}
	
	public List<Ordine> getOrdini() {
		return this.ordini;
	}
	
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Cliente"); 
		sb.append("{id=").append(id); 
		sb.append(", nome='").append(nome); 
		sb.append(", cognome=").append(cognome); 
		sb.append(", Data di nascita='").append(dataDiNascita); 
		sb.append(", email='").append(email);
		sb.append(", Data di registrazione='").append(dataDiRegistrazione); 
		sb.append(", indirizzo='").append(indirizzo);

		sb.append("}\n");
		return sb.toString();
	}
}


