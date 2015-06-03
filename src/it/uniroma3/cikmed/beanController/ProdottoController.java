package it.uniroma3.cikmed.beanController;

import java.util.List;

import it.uniroma3.cikmed.facade.ProdottoFacade;
import it.uniroma3.cikmed.model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean (name="prodottoController")
@RequestScoped
public class ProdottoController {
	
	@ManagedProperty(value="#{param.id}")
	private Long id;
	
	private String nome;
	private Float prezzo;
	private String descrizione;
	private String codice;
	private int quantita;
	
	private String errore;
	
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
			errore="Prodotto già esistente sul database. Per favore inserisci un prodotto con codice differente";
			return errore; 
		}
	}
	
	public String listProdotti() {
		this.prodotti = pFacade.getCatalogoProdotti();
		return "showProdotti?faces-redirect=true"; 
	}
	
	public String findProdotto() {
		this.prodotto = pFacade.getProdottoByID(id);
		return "showProdotto";
	}
	
	//da sistemare
	public String deleteProdotto() {   
		pFacade.deleteProdottoById(id);
		return "showProdotti";
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

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
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

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> products) {
		this.prodotti = products;
	}
}

