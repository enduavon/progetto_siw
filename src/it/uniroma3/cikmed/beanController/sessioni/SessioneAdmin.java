package it.uniroma3.cikmed.beanController.sessioni;

import java.util.List;

import it.uniroma3.cikmed.model.Amministratore;
import it.uniroma3.cikmed.model.Prodotto;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;




@ManagedBean(name="sessioneAdmin")
@SessionScoped
public class SessioneAdmin {

	private Amministratore amministratore;
	
	private List<Prodotto> prodotti; 
	
	
		

	public Amministratore getAdmin() {
		return amministratore;
	}

	public void setAdmin(Amministratore admin) {
		this.amministratore = admin;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> products) {
		this.prodotti = products;	
	}
	
	

}