package it.uniroma3.cikmed.controller;

import java.util.List;

import it.uniroma3.cikmed.facade.ProdottoFacade;
import it.uniroma3.cikmed.model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean (name="prodottoController")
public class ProdottoController {
	
	@ManagedProperty(value="#{param.id}")
	private Long id;
	private String nome;
	private Float prezzo;
	private String descrizione;
	private String codice;
	private int quantità;
	
	
	private Prodotto prodotto;
	private List<Prodotto> prodotti;
	
	@EJB (beanName="pFacade")
	private ProdottoFacade pFacade;
	
	
	
	public String creaProdotto() {
		try {
			this.prodotto = pFacade.creaProdotto(nome, codice, descrizione, prezzo, quantità);
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
	
	public String findProduct(Long id) {
		this.prodotto = pFacade.getProdottoByID(id);
		return "showProdotto";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return nome;
	}

	public void setName(String nome) {
		this.nome = nome;
	}

	public Float getPrice() {
		return prezzo;
	}

	public void setPrice(Float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescription() {
		return descrizione;
	}

	public void setDescription(String description) {
		this.descrizione = description;
	}

	public String getCode() {
		return codice;
	}

	public void setCode(String codice) {
		this.codice = codice;
	}

	public Prodotto getProduct() {
		return prodotto;
	}

	public void setProduct(Prodotto p) {
		this.prodotto = p;
	}

	public List<Prodotto> getProducts() {
		return prodotti;
	}

	public void setProducts(List<Prodotto> products) {
		this.prodotti = products;
	}
}


