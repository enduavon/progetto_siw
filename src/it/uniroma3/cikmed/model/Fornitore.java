package it.uniroma3.cikmed.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToMany;

@Entity
public class Fornitore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String iva;

	@Column(nullable = false)
	private String indirizzo;

	@Column(nullable = false, length = 10)
	private int telefono;
	
	//unique non accetta doppioni solo su code
	@Column(unique = true ,nullable = false)
	private String email;
	
	@ManyToMany
	private List<Prodotto> prodotti;

	public Fornitore(){

	}
	
	public Fornitore(String iva, String indirizzo, int telefono, String email) {
		this.email = email;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.iva = iva;
		this.prodotti = new ArrayList<Prodotto>();
	}
	
	public void addProduct(Prodotto p) {
		if(p != null)
		this.prodotti.add(p);
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

	public String getIva() {
		return this.iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String ind) {
		this.indirizzo = ind;
	}
	
	public List<Prodotto> getProdotti() {
		return this.prodotti;
	}
	
	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	@Override
	public boolean equals(Object o) {
		Prodotto product = (Prodotto) o;
		return this.getId().equals(product.getId());
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Fornitore"); 
		sb.append("{id=").append(id); 
		sb.append(", Partita iva='").append(iva); 
		sb.append(", indirizzo=").append(indirizzo); 
		sb.append(", telefono='").append(telefono); 
		sb.append(", email='").append(email);
		sb.append("}\n");
		return sb.toString();
	}
}
