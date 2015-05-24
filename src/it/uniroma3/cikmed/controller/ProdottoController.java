package it.uniroma3.cikmed.controller;

import java.util.List;

import it.uniroma3.cikmed.facade.ProdottoFacade;
import it.uniroma3.cikmed.model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean (name="prodottoController")
@ViewScoped
public class ProdottoController {
	
	@ManagedProperty(value="#{param.id}")
	private Long id;
	
	private String nome;
	private Float prezzo;
	private String descrizione;
	private String codice;
	private int quantita;
	
	
	private Prodotto prodotto;
	private List<Prodotto> prodotti;
	
	@EJB (beanName="pFacade")
	private ProdottoFacade pFacade;
	
	
	
	public String creaProdotto() {
		try {
			this.prodotto = pFacade.creaProdotto(nome, codice, descrizione, prezzo, quantita);
			return "newProdotto"; 
			}
		catch (Exception e) {
			return "error"; 
		}
	}
	
	public String listProducts() {
		this.prodotti = pFacade.getCatalogoProdotti();
		return "showProdotti"; 
	}
	
	public String findProduct() {
		this.prodotto = pFacade.getProdottoByID(id);
		return "showProdotto";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setDescrizione(String description) {
		this.descrizione = description;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto p) {
		this.prodotto = p;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> products) {
		this.prodotti = products;
	}
}

