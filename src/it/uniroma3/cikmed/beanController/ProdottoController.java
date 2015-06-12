package it.uniroma3.cikmed.beanController;

import java.io.Serializable;
import java.util.List;

import it.uniroma3.cikmed.facade.ProdottoFacade;
import it.uniroma3.cikmed.model.Prodotto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ManagedBean (name="prodottoController")
@ViewScoped
public class ProdottoController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String errore;
	
	private Prodotto prodotto;
	private Prodotto prodottoSelezionato;
	private List<Prodotto> prodotti;
	
	@EJB (beanName="pFacade")
	private ProdottoFacade pFacade;
	
	@PostConstruct
	public void init() {
		prodotti = pFacade.getCatalogoProdotti();
	} 

	
	public String listProdotti() {
		this.prodotti = pFacade.getCatalogoProdotti();
		return "showProdotti"; 
	}
	
	public String findProdotto() {
		this.prodotto = pFacade.getProdottoByID(id);
		return "showProdotto";
	}
	
	public String deleteProdotto(Prodotto p) {   
		pFacade.deleteProdotto(p);
		listProdotti();
		return "showProdotti";
	}

	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrore() {
		return errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto p) {
		this.prodotto = p;
	}

	public Prodotto getProdottoSelezionato() {
		return prodottoSelezionato;
	}


	public void setProdottoSelezionato(Prodotto prodottoSelezionato) {
		this.prodottoSelezionato = prodottoSelezionato;
	}


	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
}

