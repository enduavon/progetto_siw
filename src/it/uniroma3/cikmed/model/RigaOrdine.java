package it.uniroma3.cikmed.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "righe_ordine")
public class RigaOrdine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Float prezzo;
	
	@Column
	private int quantita;
	
	@ManyToOne
	private Prodotto prodotto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders_id")
	private Ordine ordine;
	
	public RigaOrdine() {
		
	}

	public RigaOrdine(Prodotto prodotto, Float prezzo, int quantita, Ordine ordine) {
		this.prodotto = prodotto;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.ordine = ordine; 
	}
	
	
	public float costoRigaOrdine() {
		return this.quantita*this.prezzo;
	}
	
	
	/*
	 * GETTERS & SETTERS
	 */
	
	
	public Long getID() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Prodotto getProdotto() {
		return prodotto;
	}
	
	public void setProdotto(Prodotto p) {
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

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

}
