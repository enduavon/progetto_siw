package it.uniroma3.cikmed.beanController.sessioni;


import it.uniroma3.cikmed.model.Amministratore;
import it.uniroma3.cikmed.model.Fornitore;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;




@ManagedBean(name="sessioneAdmin")
@SessionScoped
public class SessioneAdmin {

	private Amministratore admin;

	//private List<DescrizioneProdotto> descrizioniProdotto; 

	//private DescrizioneProdotto descrizioneProdotto;

	private Fornitore provider;




	public Amministratore getAdmin() {
		return admin;
	}

	public void setAdmin(Amministratore admin) {
		this.admin = admin;
	}


	//public List<DescrizioneProdotto> getDescrizioneProdotto() {
	//return descrizioneProdotto;
//}

//public void setProductDescriptions(List<DescrizioneProdotto> productDescriptions) {
	//this.descrizioneProdotto = productDescriptions;
//}

//public ProductDescription getProductDescription() {
	//return descrizioneProdotto;
//}

//public void setProductDescription(ProductDescription productDescription) {
	//this.descrizioneProdotto = productDescription;
//}

public Fornitore getFornitore() {
	return provider;
}

public void setFornitore(Fornitore provider) {
	this.provider = provider;
}



}