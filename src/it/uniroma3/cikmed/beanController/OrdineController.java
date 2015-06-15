package it.uniroma3.cikmed.beanController;

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

	//	@ManagedProperty(value="#{param.id}")
	private Long id;
	
	private String stato;
	private Calendar dataApertura;
	private Calendar dataChiusura;
	private Calendar dataEvasione;
	
	private Ordine ordine;
	private List<Ordine> ordini;
	private List<Ordine> ordiniEvasi;
	private List<Ordine> ordiniCliente;
	private RigaOrdine rigaOrdine;
	List<RigaOrdine> righeOrdine;
	
	private Prodotto prodottoCorrente;
	private int quantita;
	
	private String errore;
	
//	@ManagedProperty(value="#{loginCliente.clienteLoggato}") //giusto
	private Cliente clienteCorrente;
	
	@EJB (beanName="ordFacade")
	private OrdineFacade oFacade;
	@EJB (beanName="rigaordFacade") 
	private RigaOrdineFacade roFacade;
	@EJB (beanName="pFacade")
	private ProdottoFacade pFacade;
	
	@PostConstruct
	public void init() {
		ordini = oFacade.getListaOrdiniByStato("chiuso");
    	ordiniCliente = oFacade.getOrdiniCliente(clienteCorrente);
		ordiniEvasi = oFacade.getListaOrdiniByStato("evaso");
	} 
	
	
	public String creaOrdine(Cliente clienteCorrente) {
		
		if (oFacade.getOrdineApertoByCliente("aperto", clienteCorrente)!=null)
			return setErrore("Hai già un ordine attualmente aperto. Appena chiudi e confermi"
					+ " l'ordine attuale, allora potrai fare un altro ordine.");
		
		else {
			try {
				this.ordine = oFacade.creaOrdine(Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome")), clienteCorrente);
				oFacade.updateOrdine(ordine);
				System.out.println("" +ordine.getCliente().getId()+ "");				
				return "newOrdine"; 
			}

			catch (Exception e) {
				return "error"; 
			}
		}
	}
	
	public String listaOrdiniChiusi() {
		this.ordini = oFacade.getListaOrdiniByStato("chiuso");
		return "showOrdini"; 
	}
	
	public String listaOrdiniEvasi() {
		this.setOrdiniEvasi(oFacade.getListaOrdiniByStato("evaso"));
		return "showOrdiniEvasi"; 
	}
	
	public String listaOrdiniCliente() {
		this.setOrdiniCliente(oFacade.getOrdiniCliente(clienteCorrente));
		return "showOrdiniCliente"; 
	}
	
	//mettere dettagli sulle righe ordine (carrello?)
	public String findOrdine() {
		this.ordine = oFacade.getOrdineByID(id);
		return "showOrdine";
	}
	
	public String deleteOrdine(Ordine o) {  
		
		if (oFacade.getOrdineByID(id).isAperto()) {
			oFacade.deleteOrdine(o);
			listaOrdiniCliente();
			return "showOrdiniCliente";
		}

		else {
			return setErrore("Non puoi più cancellare questo ordine.");
		}
	}	
	
	public String closeOrdine() { 

		if (oFacade.getOrdineByID(id).isAperto()) {
			oFacade.closeOrdineByID(id);
			return "showOrdineCompletato?faces-redirect=true";
		}

		else {
			return setErrore("Non puoi chiudere questo ordine.");
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


	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}

	public void setRigaOrdine(RigaOrdine rigaOrdine) {
		this.rigaOrdine = rigaOrdine;
	}
	
	

	public List<RigaOrdine> getRigheOrdine() {
		return righeOrdine;
	}


	public void setRigheOrdine(List<RigaOrdine> righeOrdine) {
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
