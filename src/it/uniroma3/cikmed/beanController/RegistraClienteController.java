package it.uniroma3.cikmed.beanController;


import it.uniroma3.cikmed.facade.ClienteFacade;


import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="registraCliente")
@RequestScoped
public class RegistraClienteController {

	@EJB(beanName="clienteFacade")
	private ClienteFacade facade;

	private String email;
	private String nickname;
	private String password;
	private String nome;
	private String cognome;
	private Date dataNascita;

	private String registrazioneSbagliata;
/*
 * adesso non mi serve la sessione,in futuro servirà quando ci sarà anche un 
 * admin e il database dovrà avere memoria di chi è chi e di quali ordini
 *  esso si associa ecc...
 */
//	@ManagedProperty(value="#{sessioneCliente}")
//	private SessioneCliente sessione;

	public String registraCliente() {
		if(!facade.esisteEmail(email)) {
//			Cliente customer = 
					facade.creaCliente(nome, nickname, password, cognome, dataNascita, email);
			//sessione.setCliente(customer);
			return "mostraCliente";
		}
		else {
			setRegisterErrore("Email esistente");
			return "registrazioneCliente";
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
	public String getNome() {
		return nome;
	}

	public void setNome(String firstName) {
		this.nome = firstName;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String firstName) {
		this.nickname = firstName;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String lastName) {
		this.cognome = lastName;
	}

	public Date getDataDiNascita() {
		return dataNascita;
	}

	public void setDataDiNascita(Date birthDay) {
		this.dataNascita = birthDay;
	}

	public String getRegisterErrore() {
		return registrazioneSbagliata;
	}

	public void setRegisterErrore(String registerError) {
		this.registrazioneSbagliata = registerError;
	}

//	public SessioneCliente getSession() {
//		return sessione;
//	}

//	public void setSession(SessioneCliente session) {
//		this.sessione = session;
//	}
	
	
}