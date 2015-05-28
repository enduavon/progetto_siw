package it.uniroma3.cikmed.beanController;

import it.uniroma3.cikmed.facade.AmministratoreFacade;
import it.uniroma3.cikmed.model.Amministratore;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="registraAdmin")
@RequestScoped
public class RegistraAdminController {
	
	@EJB(beanName="amministratoreFacade")
	private AmministratoreFacade facade;

	private String email;
	private String password;
	private String nickname;
	private Amministratore adminRegistrato;

	private String errore;

	public String registraAdmin() {
		try {
			if(facade.verificaEmail(email)!=true) {

				adminRegistrato = facade.creaAdmin(nickname, password, email);
				facade.updateAdmin(adminRegistrato);
				return "mostraAdmin"; } }
		catch (Exception e) {
			if(e.getClass().getName().equals("javax.ejb.EJBTransactionRolledbackException")){
				setErrore("Email già esistente. Utilizza un altro indirizzo email per registrarti.");
				return "registrazioneAdmin"; }
			else {
				setErrore("Impossibile registrarsi");
				return "registrazioneAdmin";
			} }
		return "registrazioneAdmin";
	}		


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nick) {
		this.nickname = nick;
	}

	public String getErrore() {
		return errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}


	public Amministratore getAdminRegistrato() {
		return adminRegistrato;
	}


	public void setAdminRegistrato(Amministratore adminRegistrato) {
		this.adminRegistrato = adminRegistrato;
	}
	
}