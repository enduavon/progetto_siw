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
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	private List<Ordine> ordiniCliente;
	private RigaOrdine rigaOrdine;
	private Map<Prodotto,RigaOrdine> righeOrdine;
	
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
		ordini = oFacade.getListaOrdini();
		listaOrdiniCliente();
	} 
	
	
	public String creaOrdine(Cliente clienteCorrente) {
		
		if (oFacade.getOrdineApertoByCliente("aperto", clienteCorrente)!=null)
			return setErrore("Hai già un ordine attualmente aperto. Appena chiudi e confermi"
					+ " l'ordine attuale, allora potrai fare un altro ordine.");
		else {
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
	}
	
	public String listaOrdini() {
		this.ordini = oFacade.getListaOrdini();
		return "showOrdini"; 
	}
	
	public String listaOrdiniCliente() {
		this.ordiniCliente = oFacade.getOrdiniCliente(clienteCorrente);
		return "showOrdiniCliente"; 
	}
	
	public String findOrdine() {
		this.ordine = oFacade.getOrdineByID(id);
		return "showOrdine";
	}
	
	public String deleteOrdine() { 
		
		if (oFacade.getOrdineByID(id).isAperto()) {
			oFacade.deleteOrdineByID(id);
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
	 
	
	public String aggiungiRigaOrdine() {
		
		if (quantita>prodottoCorrente.getQuantita()) 
			return setErrore("Devi inserire una quantità di prodotto minore di quella disponibile in magazzino");
		
		if (quantita==0)
			return setErrore("Non puoi aggiungere zero prodotti all'ordine.");

		ordine = oFacade.getOrdineApertoByCliente("aperto", clienteCorrente);
		
		//problema da risolvere: dà sempre null come risultato perchè non trova l'ordine della riga ordine
		if (roFacade.getRigaOrdineProdottoByOrdine(prodottoCorrente, ordine)!=null) { //esiste già una riga ordine per quel prodotto	
			rigaOrdine = roFacade.getRigaOrdineProdottoByOrdine(prodottoCorrente, ordine);
			roFacade.increaseQuantitaRigaOrdine(rigaOrdine, quantita);
			pFacade.decreaseQuantitaProdotto(prodottoCorrente, quantita);
			return "updateRigaOrdine";//mostro la riga ordine aggiornata o l'ordine aggiornato?
		}

		else { //se non esiste la riga ordine per quel prodotto e per quell'ordine, allora la creo
			rigaOrdine = roFacade.creaRigaOrdine(prodottoCorrente, prodottoCorrente.getPrezzo(), quantita, ordine);
			pFacade.decreaseQuantitaProdotto(prodottoCorrente, quantita);
			return "showRigaOrdine";
		}  //mostro i dettagli del prodotto che ho aggiunto, con la quantità da me scelta
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
