package it.uniroma3.cikmed.model;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


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
	private Calendar dataDiNascita;

	@Column(nullable = false)
	private Calendar dataDiRegistrazione;
	
	//unique non accetta doppioni solo su code
	@Column(unique = true ,nullable = false)
	private String email;
	
	@OneToOne
	private Indirizzo indirizzo;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
	private List<Ordine> ordini;


	public Cliente(){

	}
	
	public Cliente(String nome, String cognome, String nickname, String password, 
			Calendar dataDiNascita, Calendar dataDiRegistrazione,
			Indirizzo indirizzo, String email) {
		this.nickname = nickname;
		this.password = password;
		this.cognome = cognome;
		this.nome = nome;
		this.dataDiNascita = dataDiNascita;
		this.dataDiRegistrazione = dataDiRegistrazione;
		this.email = email;
		this.indirizzo = indirizzo;
		this.ordini = new ArrayList<Ordine>();
	}
	
	
	
	public void addOrdine (Ordine o) {
		this.ordini.add(o);
	}

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

	public Calendar getDataDiNascita() {
		return this.dataDiNascita;
	}

	public void setDataDiNascita(Calendar data) {
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
	
	public boolean verificaPassword(String password) {
		return this.password.equals(password);
	}
	
	//fare anche il setOrdini oppure no? a cosa mi potrebbe servire?
	public List<Ordine> getOrdini() {
		return this.ordini;
	}
	
	
	@Override
	public boolean equals(Object o) {
		Cliente client = (Cliente) o;
		return this.getId().equals(client.getId());
	}
	@Override
	public int hashCode() {
		return this.id.hashCode();
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


