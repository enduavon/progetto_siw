package it.uniroma3.cikmed.beanController;

import java.util.List;

import it.uniroma3.cikmed.facade.OrdineFacade;
import it.uniroma3.cikmed.facade.ProdottoFacade;
import it.uniroma3.cikmed.facade.RigaOrdineFacade;
import it.uniroma3.cikmed.model.Ordine;
import it.uniroma3.cikmed.model.Prodotto;
import it.uniroma3.cikmed.model.RigaOrdine;

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
	
	List<RigaOrdine> righeOrdine;
	
	private String errore;
	
	@EJB (beanName="pFacade")
	private ProdottoFacade pFacade;	
	@EJB (beanName="rigaordFacade") 
	private RigaOrdineFacade roFacade;
	@EJB (beanName="ordFacade")
	private OrdineFacade oFacade;
	
	
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
	
	
	public String evadeOrdine(Ordine o) { 

		righeOrdine = roFacade.getRigheOrdineByOrdine(o);

		for (RigaOrdine ro: righeOrdine) {
			pFacade.decreaseQuantitaProdotto(ro.getProdotto(), ro.getQuantita());
		}

		oFacade.evadeOrdine(o); 

		return "showOrdineEvaso";
	}	//4901  2015-06-14 02:01:52.052 aperto 851
	    //4950  2015-06-14 05:50:30.059 chiuso 851


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
	
	
	
	public List<RigaOrdine> getRigheOrdine() {
		return righeOrdine;
	}


	public void setRigheOrdine(List<RigaOrdine> righeOrdine) {
		this.righeOrdine = righeOrdine;
	}


	public String getErrore() {
		return errore;
	}

	public String setErrore(String errore) {
		this.errore = errore;
		return errore;
	}

}
