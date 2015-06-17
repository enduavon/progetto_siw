package it.uniroma3.cikmed.beanController;

import it.uniroma3.cikmed.facade.ClienteFacade;
import it.uniroma3.cikmed.facade.OrdineFacade;
import it.uniroma3.cikmed.facade.ProdottoFacade;
import it.uniroma3.cikmed.facade.RigaOrdineFacade;
import it.uniroma3.cikmed.model.Cliente;
import it.uniroma3.cikmed.model.Ordine;
import it.uniroma3.cikmed.model.Prodotto;
import it.uniroma3.cikmed.model.RigaOrdine;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="ordineController")
@ViewScoped
public class OrdineController implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private long idOrdine;
	
	private String stato;
	private Calendar dataApertura;
	private Calendar dataChiusura;
	private Calendar dataEvasione;
	
	private Ordine ordine;
	private List<Ordine> ordini;
	private List<Ordine> ordiniEvasi;
	private List<Ordine> ordiniCliente;
	
	List<RigaOrdine> righeOrdine;
	
	private Prodotto prodottoCorrente;
	private int quantita;
	
	private String errore;
	
	private Cliente clienteCorrente;
	
	@EJB (beanName="ordFacade")
	private OrdineFacade oFacade;
	@EJB (beanName="rigaordFacade") 
	private RigaOrdineFacade roFacade;
	@EJB (beanName="pFacade")
	private ProdottoFacade pFacade;
	@EJB (beanName="clienteFacade")
	private ClienteFacade cFacade;
	
	@PostConstruct
	public void init() {
		ordini = oFacade.getListaOrdiniByStato("chiuso");
    	ordiniCliente = oFacade.getOrdiniCliente(clienteCorrente);
		ordiniEvasi = oFacade.getListaOrdiniByStato("evaso");
	} 
	
	
	public String listaOrdiniChiusi() {
		this.ordini = oFacade.getListaOrdiniByStato("chiuso");
		return "showOrdini"; 
	}
	
	public String listaOrdiniEvasi() {
		this.setOrdiniEvasi(oFacade.getListaOrdiniByStato("evaso"));
		return "showOrdiniEvasi"; 
	}
	
	public void listaOrdiniCliente() {
		clienteCorrente = cFacade.getClienteByID(id);
		this.setOrdiniCliente(oFacade.getOrdiniCliente(clienteCorrente));
//		return "showOrdiniCliente"; 
	}
	
	
	public void deleteOrdine(Ordine o) {  //OK 
		
		if (oFacade.checkStatoOrdine(o.getId(), "aperto")!=null) { 
		    roFacade.deleteRigheOrdine(o);
			oFacade.deleteOrdine(o); 
			listaOrdiniCliente();
		}

		else {
			setErrore("Non puoi più cancellare questo ordine.");
		}
	}	
	
	public void closeOrdine(Ordine o) { //OK

		if (oFacade.checkStatoOrdine(o.getId(), "aperto")!=null) {
			oFacade.closeOrdineByID(o.getId());
			listaOrdiniCliente();
		}

		else {
			setErrore("Hai già confermato l'ordine!");
		}
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
	
	public long getIdOrdine() {
		return idOrdine;
	}


	public void setIdOrdine(long idOrdine) {
		this.idOrdine = idOrdine;
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

	public List<Ordine> getOrdiniEvasi() {
		return ordiniEvasi;
	}


	public void setOrdiniEvasi(List<Ordine> ordiniEvasi) {
		this.ordiniEvasi = ordiniEvasi;
	}


	public List<Ordine> getOrdiniCliente() {
		return ordiniCliente;
	}


	public void setOrdiniCliente(List<Ordine> ordiniCliente) {
		this.ordiniCliente = ordiniCliente;
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
