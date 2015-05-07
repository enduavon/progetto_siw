package it.uniroma3.progetto.persistenza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Indirizzo {

	public Indirizzo(){

	}

	public Indirizzo(String via, String città, String nazione, String codicePostale, 
			String stato) {
		this.via = via;
		this.città = città;
		this.nazione = nazione;
		this.codicePostale = codicePostale;
		this.stato = stato;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String via;

	@Column(nullable = false)
	private String città;
	private String nazione;

	@Column(nullable = false)
	private String codicePostale;

	//può essere nullo, perchè non è detto che una nazione abbia degli stati come gli USA
	@Column(nullable = true)
	private String stato;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStreet() {
		return via;
	}
	public void setStreet(String street) {
		this.via = street;
	}
	public String getCity() {
		return città;
	}
	public void setCity(String city) {
		this.città = city;
	}
	public String getZipCode() {
		return codicePostale;
	}
	public void setZipCode(String zipCode) {
		this.codicePostale = zipCode;
	}
	public String getCountry() {
		return nazione;
	}
	public void setCountry(String country) {
		this.nazione = country;
	}
	public String getState() {
		return stato;
	}
	public void setState(String state) {
		this.stato = state;
	}
	
	@Override
	public int hashCode() {
		return this.codicePostale.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Indirizzo ind =(Indirizzo)obj;
		return (this.getZipCode().equals(ind.getZipCode()));
	}

}
