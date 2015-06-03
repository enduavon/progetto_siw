package it.uniroma3.cikmed.beanController;

import it.uniroma3.cikmed.facade.OrdineFacade;
import it.uniroma3.cikmed.facade.RigaOrdineFacade;
import it.uniroma3.cikmed.model.Cliente;
import it.uniroma3.cikmed.model.Ordine;
import it.uniroma3.cikmed.model.Prodotto;
import it.uniroma3.cikmed.model.RigaOrdine;

//import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
//import javax.faces.bean.SessionScoped;

@ManagedBean(name="ordineController")
@RequestScoped
public class OrdineController {
	
	@ManagedProperty(value="#{param.id}")
	private Long id;
	
	private String stato;
	private Calendar dataApertura;
	private Calendar dataChiusura;
	private Calendar dataEvasione;
	
	private Ordine ordine;
	private List<Ordine> ordini;
	
	private Map<Prodotto,RigaOrdine> righeOrdine;
	
	private Prodotto prodottoCorrente;
	private int quantità;
	
	private String errore;
	
	@ManagedProperty(value="#{loginCliente.clienteLoggato}")
	private Cliente clienteCorrente;
	
	@EJB (beanName="ordFacade")
	private OrdineFacade oFacade;
	@EJB (beanName="rigaordFacade")
	private RigaOrdineFacade roFacade;
	
	
	public String creaOrdine() {
		try {
			this.ordine = oFacade.creaOrdine(Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome")), clienteCorrente);
//			ordine.setRigheOrdine((new ArrayList<RigaOrdine>(righeOrdine.values())));
			oFacade.updateOrdine(ordine);;
			return "newOrdine"; 
			}
		catch (Exception e) {
			return "error"; 
		}
	}
	
	public String listaOrdini() {
		this.ordini = oFacade.getListaOrdini();
		return "showOrdini"; 
	}
	
	public String findOrdine() {
		this.ordine = oFacade.getOrdineByID(id);
		return "showOrdine";
	}	
	
	
	public String aggiungiRigaOrdine() {
		RigaOrdine ro = roFacade.getRigaOrdineByProdotto(prodottoCorrente);
		if (quantità>prodottoCorrente.getQuantita())
			return setErrore("Devi inserire una quantità di prodotto minore di quella disponibile in magazzino");
		
		if(ordine.getRigheOrdine().contains(ro)){
			ro.setQuantita(ro.getQuantita()+this.quantità);
			roFacade.updateRigaOrdine(ro);
		} else {
			ro = roFacade.creaRigaOrdine(prodottoCorrente, prodottoCorrente.getPrezzo(), quantità);
			ordine.getRigheOrdine().add(ro);
			oFacade.updateOrdine(ordine);
		}
		return "showRigaOrdine";  //mostro i dettagli del prodotto che ho aggiunto, con la quantità da me scelta
	}

				
	
	
	/*
	 * GETTERS & SETTERS
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Calendar getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Calendar dataApertura) {
		this.dataApertura = dataApertura;
	}

	public Calendar getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(Calendar dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public Calendar getDataEvasione() {
		return dataEvasione;
	}

	public void setDataEvasione(Calendar dataEvasione) {
		this.dataEvasione = dataEvasione;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public Map<Prodotto,RigaOrdine> getRigheOrdine() {
		return righeOrdine;
	}

	public void setRigheOrdine(Map<Prodotto,RigaOrdine> righeOrdine) {
		this.righeOrdine = righeOrdine;
	}

	public Cliente getClienteCorrente() {
		return clienteCorrente;
	}

	public void setClienteCorrente(Cliente clienteCorrente) {
		this.clienteCorrente = clienteCorrente;
	}

	public Prodotto getProdottoCorrente() {
		return prodottoCorrente;
	}

	public void setProdottoCorrente(Prodotto prodottoCorrente) {
		this.prodottoCorrente = prodottoCorrente;
	}
	
	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

	public String getErrore() {
		return errore;
	}

	public String setErrore(String errore) {
		this.errore = errore;
		return errore;
	}

}
