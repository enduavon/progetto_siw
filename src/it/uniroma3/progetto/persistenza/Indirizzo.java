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

	public Indirizzo(String via, String città, String stato, String codiceZip, String paese) {
		this.strada = via;
		this.città = città;
		this.stato = stato;
		this.codiceZip = codiceZip;
		this.paese = paese;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String strada;

	@Column(nullable = false)
	private String città;
	private String stato;

	@Column(nullable = false)
	private String codiceZip;

	@Column(nullable = false)
	private String paese;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStreet() {
		return strada;
	}
	public void setStreet(String street) {
		this.strada = street;
	}
	public String getCity() {
		return città;
	}
	public void setCity(String city) {
		this.città = city;
	}
	public String getZipCode() {
		return codiceZip;
	}
	public void setZipCode(String zipCode) {
		this.codiceZip = zipCode;
	}
	public String getCountry() {
		return paese;
	}
	public void setCountry(String country) {
		this.paese = country;
	}
	public String getState() {
		return stato;
	}
	public void setState(String state) {
		this.stato = state;
	}
	@Override
	public int hashCode() {
		return this.codiceZip.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		Indirizzo ind =(Indirizzo)obj;
		return (this.getZipCode().equals(ind.getZipCode()));
	}

}
