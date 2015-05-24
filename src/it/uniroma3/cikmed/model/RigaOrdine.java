package it.uniroma3.cikmed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "riga_ordine")
public class RigaOrdine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Float prezzo;
	@Column
	private int quantita;
	@ManyToOne
	private Prodotto prodotto;
	
	public RigaOrdine() {
		
	}

	public RigaOrdine(Prodotto prodotto, Float prezzo, int quantita) {
		this.prodotto = prodotto;
		this.prezzo = prezzo;
		this.quantita = quantita;
	}
	
	
	public Long getID() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Prodotto getProdottoDellaRigaOrdine() {
		return prodotto;
	}
	
	public void setProdottoDellaRigaOrdine(Prodotto p) {
		this.prodotto = p;
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

}
