package it.uniroma3.cikmed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Indirizzo {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String via;

	@Column(nullable = false)
	private String citta;
	private String nazione;

	@Column(nullable = false)
	private String codicePostale;

	//può essere nullo, perchè non è detto che una nazione abbia degli stati come gli USA
	@Column(nullable = true)
	private String stato;

	
	public Indirizzo(){

	}

	public Indirizzo(String via, String citta, String nazione, String codicePostale, 
			String stato) {
		this.via = via;
		this.citta = citta;
		this.nazione = nazione;
		this.codicePostale = codicePostale;
		this.stato = stato;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getCodicePostale() {
		return codicePostale;
	}
	public void setCodicePostale(String codicePostale) {
		this.codicePostale = codicePostale;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	@Override
	public int hashCode() {
		return this.codicePostale.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Indirizzo ind =(Indirizzo)obj;
		return (this.getCodicePostale().equals(ind.getCodicePostale()));
	}

}
