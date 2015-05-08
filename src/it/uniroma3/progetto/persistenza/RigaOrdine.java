package it.uniroma3.progetto.persistenza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RigaOrdine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Float prezzo;
	@Column
	private int quantità;
	@Column
	private Prodotto prodotto;

	public RigaOrdine(Prodotto prodotto, Float prezzo, int quantità) {
		this.prodotto = prodotto;
		this.prezzo = prezzo;
		this.quantità = quantità;
	}
	
	public RigaOrdine() {
		
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
	
	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int q) {
		this.quantità = q;
	}

}
