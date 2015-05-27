package it.uniroma3.cikmed.beanController;

import it.uniroma3.cikmed.beanController.sessioni.SessioneAdmin;
import it.uniroma3.cikmed.facade.AmministratoreFacade;
import it.uniroma3.cikmed.model.Amministratore;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


@ManagedBean(name="registraAdmin")
public class RegistraAdminController {
	@EJB(beanName="amministratoreFacade")
	private AmministratoreFacade facade;

	private String email;
	private String password;
	private String nickname;


	private String registrazioneErrata;

	@ManagedProperty(value="#{sessioneAdmin}")
	private SessioneAdmin sessione;

	public String registraAdmin() {
		if(!facade.esisteEmail(email)) {
			Amministratore admin = 
					facade.creaAdmin(nickname, password, email);
			sessione.setAdmin(admin);
			return "mostraAdmin";
		}
		else {
			setRegistrazioneErrata("Email gia esistente");
			return "registrazioneAdmin";
		}
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

	public String getRegistrazioneErrata() {
		return registrazioneErrata;
	}

	public void setRegistrazioneErrata(String registerError) {
		this.registrazioneErrata = registerError;
	}

	public SessioneAdmin getSession() {
		return sessione;
	}

	public void setSession(SessioneAdmin session) {
		this.sessione = session;
	}
}