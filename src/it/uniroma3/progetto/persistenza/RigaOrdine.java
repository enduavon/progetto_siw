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
	private int quantit�;
	@Column
	private Prodotto prodotto;

	public RigaOrdine(Prodotto prodotto, Float prezzo, int quantit�) {
		this.prodotto = prodotto;
		this.prezzo = prezzo;
		this.quantit� = quantit�;
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
	
	public int getQuantit�() {
		return quantit�;
	}

	public void setQuantit�(int q) {
		this.quantit� = q;
	}

}
