package it.uniroma3.cikmed.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToMany;

@Entity
public class Prodotto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;
	private Float prezzo;

	@Column(length = 2000)
	private String descrizione;
	
	//unique non accetta doppioni solo su code
	@Column(unique = true ,nullable = false)
	private String codice;

	@Column(nullable = false)
	private int quantita;
	
	@ManyToMany(mappedBy = "prodotti")
	private List<Fornitore> fornitori;

	
	public Prodotto(){

	}
	
	public Prodotto(String nome, String codice, String descrizione, Float prezzo, int quantita) {
		this.nome = nome;
		this.codice = codice;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.fornitori = new ArrayList<Fornitore>();
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

	public void setNome(String name) {
		this.nome = name;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String code) {
		this.codice = code;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String description) {
		this.descrizione = description;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float price) {
		this.prezzo = price;
	}
	
	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int q) {
		this.quantita = q;
	}
	
	public List<Fornitore> getFornitori() {
		return fornitori;
	}

	public void setFornitori(List<Fornitore> fornitori) {
		this.fornitori = fornitori;
	}


	@Override
	public boolean equals(Object o) {
		Prodotto product = (Prodotto) o;
		return this.getCodice().equals(product.getCodice());
	}
	
	@Override
	public int hashCode() {
		return this.codice.hashCode();
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Prodotto"); 
		sb.append("{id=").append(id); 
		sb.append(", name='").append(nome); 
		sb.append(", price=").append(prezzo); 
		sb.append(", description='").append(descrizione); 
		sb.append(", code='").append(codice);
		sb.append("}\n");
		return sb.toString();
	}
}
