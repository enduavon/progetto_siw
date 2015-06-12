package it.uniroma3.cikmed.beanController;

import it.uniroma3.cikmed.facade.ProdottoFacade;
import it.uniroma3.cikmed.model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="richiesta")
@RequestScoped
public class RequestController {
	
	private Prodotto prodotto;
	private String codice;
	private String nome;
	private Float prezzo;
	private int quantita;
	private String descrizione;
	
	private String errore;
	
	@EJB (beanName="pFacade")
	private ProdottoFacade pFacade;	
	
	
	public String creaProdotto() {
		try {
			this.prodotto = pFacade.creaProdotto(nome, codice, descrizione, prezzo, quantita);
			return "newProdotto"; 
			}
		catch (Exception e) {
			errore="Prodotto già esistente sul database. Per favore inserisci un prodotto con codice differente";
			return errore; 
		}
	}


	/*
	 * GETTERS & SETTERS
	 */	

	
	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public String getErrore() {
		return errore;
	}

	public String setErrore(String errore) {
		this.errore = errore;
		return errore;
	}

}
