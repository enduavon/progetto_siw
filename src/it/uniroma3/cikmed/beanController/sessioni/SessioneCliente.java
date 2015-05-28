package it.uniroma3.cikmed.beanController.sessioni;

import java.util.List;

import it.uniroma3.cikmed.facade.ClienteFacade;
import it.uniroma3.cikmed.model.Cliente;
import it.uniroma3.cikmed.model.Ordine;
import it.uniroma3.cikmed.model.Prodotto;
import it.uniroma3.cikmed.model.RigaOrdine;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="sessioneCliente")
@SessionScoped
public class SessioneCliente {
	
	/*
	 * controller per cliente
	 */
	//@ManagedProperty(value="#{param.id}")
	
	private Cliente clienteCorrente;
	private ClienteFacade clienteFacade;

	private Ordine ordineCorrente;
	private RigaOrdine rigaOrdineCorrente;
	private List<Ordine> ordini;

	private List<Prodotto> prodotti;
	private Prodotto prodottoCorrente;


	public String findCliente(Long id) {
		this.clienteCorrente = clienteFacade.getClienteByID(id);
		return "showCliente";
	}

	public String riepilogoCliente() {
		return "mostraCliente";
	}


	public String listClienti() {
		clienteFacade.getTuttiClienti();
		return "mostraClienti"; 
	}

	public Cliente getCliente() {
		return clienteCorrente;
	}

	public void setCliente(Cliente customer) {
		this.clienteCorrente = customer;
	}

	public Ordine getOrdine() {
		return ordineCorrente;
	}

	public void setOrdine(Ordine ordine) {
		this.ordineCorrente = ordine;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public Prodotto getProdotto() {
		return prodottoCorrente;
	}

	public void setProdotto(Prodotto product) {
		this.prodottoCorrente = product;
	}

	public RigaOrdine getRigaOrdine() {
		return rigaOrdineCorrente;
	}

	public void setRigaOrdine(RigaOrdine orderLine) {
		this.rigaOrdineCorrente = orderLine;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> orders) {
		this.ordini = orders;
	}

}