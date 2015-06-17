package it.uniroma3.cikmed.beanController;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import it.uniroma3.cikmed.facade.ClienteFacade;
import it.uniroma3.cikmed.facade.IndirizzoFacade;
import it.uniroma3.cikmed.facade.OrdineFacade;
import it.uniroma3.cikmed.facade.ProdottoFacade;
import it.uniroma3.cikmed.facade.RigaOrdineFacade;
import it.uniroma3.cikmed.model.Cliente;
import it.uniroma3.cikmed.model.Indirizzo;
import it.uniroma3.cikmed.model.Ordine;
import it.uniroma3.cikmed.model.Prodotto;
import it.uniroma3.cikmed.model.RigaOrdine;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/* Questo controller ha la responsabilità di eseguire numerosi metodi per classi diverse tra loro, e per questo
 * motivo è stato definito "Acme", che in questo caso sta per "A Controller that makes everything": è un riadattamento
 * del più celebre motivo "A company that makes everything".
 */
@ManagedBean(name="acme")
@RequestScoped
public class AcmeController {
	
	private long id;
	
	private Prodotto prodotto;
	private String codice;
	private String nome;
	private Float prezzo;
	
	private int quantita;
	
	private String descrizione;
	
	Ordine ordine;
	RigaOrdine rigaOrdine;
	List<RigaOrdine> righeOrdine;
	
	Cliente clienteCorrente;
	
	private Indirizzo indirizzo;
	private String via;
	private String citta;
	private String nazione;
	private String codicePostale;
	private String stato;
	
	private String errore;
	
	@EJB (beanName="clienteFacade")
	private ClienteFacade cFacade;
	@EJB (beanName="pFacade")
	private ProdottoFacade pFacade;	
	@EJB (beanName="rigaordFacade") 
	private RigaOrdineFacade roFacade;
	@EJB (beanName="ordFacade")
	private OrdineFacade oFacade;
	@EJB (beanName="indFacade")
	private IndirizzoFacade indFacade;
	
	
	
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
	
	
	public String creaIndirizzo(Cliente clienteCorrente) {

		if (cFacade.getClienteByID(clienteCorrente.getId()).getIndirizzo()!=null)
			return setErrore("Hai già inserito il tuo indirizzo precedentemente. In caso, puoi modificarlo"
					+ "seguendo le istruzioni sotto.");

		else {
			try {
				this.indirizzo = indFacade.creaIndirizzo(via, citta, nazione, codicePostale, stato);
				indFacade.updateIndirizzo(indirizzo);
				cFacade.addIndirizzo(clienteCorrente, indirizzo);	
				return "newIndirizzo"; 
			}

			catch (Exception e) {
				return setErrore("Non è stato possibile inserire l'indirizzo."); 
			}
		}
	}
	
	public void dettagliIndirizzo(Cliente cliente) {
		Indirizzo i = cFacade.getClienteByID(cliente.getId()).getIndirizzo();
		System.out.println(""+i.getId()+ "");
		indirizzo = indFacade.getIndirizzoByID(i.getId());
	}
	
	
	public String aggiungiRigaOrdine() {

		Prodotto prodottoCorrente = pFacade.getProdottoByCodice(codice);
		
		if (quantita>prodottoCorrente.getQuantita()) 
			return setErrore("Devi inserire una quantità di prodotto minore di quella disponibile in magazzino");

		if (quantita==0)
		    return setErrore("Non puoi aggiungere zero prodotti all'ordine.");

		if (oFacade.getOrdineApertoByCliente("aperto", clienteCorrente)==null)
			return setErrore("Non hai ancora aperto un ordine, vai a crearne uno!");
		
		Ordine ordineCorrente = oFacade.getOrdineApertoByCliente("aperto", clienteCorrente);

		if (roFacade.getRigaOrdineProdottoByOrdine(prodottoCorrente, ordineCorrente)!=null) { //cioè esiste già una riga ordine per quel prodotto	
			rigaOrdine = roFacade.getRigaOrdineProdottoByOrdine(prodottoCorrente, ordineCorrente);
			roFacade.increaseQuantitaRigaOrdine(rigaOrdine, quantita);
			oFacade.increasePrezzoTotale(ordineCorrente, rigaOrdine.costoRigaOrdine());
			return "updateRigaOrdine";//mostro la riga ordine aggiornata o l'ordine aggiornato?
		}

		else { //se non esiste la riga ordine per quel prodotto e per quell'ordine, allora la creo
			rigaOrdine = roFacade.creaRigaOrdine(prodottoCorrente, prodottoCorrente.getPrezzo(), quantita, ordineCorrente);
			oFacade.increasePrezzoTotale(ordineCorrente, rigaOrdine.costoRigaOrdine());
			return "showRigaOrdine";
		}  //mostro i dettagli del prodotto che ho aggiunto, con la quantità da me scelta
	}
	

	public String dettagliOrdine() {
		ordine = oFacade.getOrdineByID(id);
		righeOrdine = roFacade.getRigheOrdineByOrdine(ordine);
		return "showOrdine";
	}

	
	public String evadeOrdine(Ordine o) { 

		righeOrdine = roFacade.getRigheOrdineByOrdine(o);

		for (RigaOrdine ro: righeOrdine) {
			pFacade.decreaseQuantitaProdotto(ro.getProdotto(), ro.getQuantita());
		}

		oFacade.evadeOrdine(o); 

		return "showOrdineEvaso";
	}	

	
	
	/*
	 * GETTERS & SETTERS
	 */	

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}	
	
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
	
		
	public Ordine getOrdine() {
		return ordine;
	}


	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
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


	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getNazione() {
		return nazione;
	}
	
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	public String getCodicePostale() {
		return codicePostale;
	}
	
	public void setCodicePostale(String codicePostale) {
		this.codicePostale = codicePostale;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public String getErrore() {
		return errore;
	}

	public String setErrore(String errore) {
		this.errore = errore;
		return errore;
	}

}
