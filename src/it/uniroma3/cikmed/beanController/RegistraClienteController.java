package it.uniroma3.cikmed.beanController;


import it.uniroma3.cikmed.beanController.sessioni.SessioneCliente;
import it.uniroma3.cikmed.facade.ClienteFacade;






import it.uniroma3.cikmed.model.Cliente;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	private Date dataDiNascita;
	private Calendar dataDiRegistrazione;

	private String registrazioneSbagliata;
/*
 * adesso non mi serve la sessione,in futuro servir� quando ci sar� anche un 
 * admin e il database dovr� avere memoria di chi � chi e di quali ordini
 *  esso si associa ecc...
 */
	@ManagedProperty(value="#{sessioneCliente}")
	private SessioneCliente sessione;

	public String registraCliente() {
		if(facade.getClienteByEmail(email)==false) {
			
			setDataDiRegistrazione(Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome")));
			Cliente customer = 	facade.creaCliente(nome, nickname, password, cognome, dataDiNascita, email);
			sessione.setCliente(customer);
			return "mostraCliente";
		}
		else {
			setRegisterErrore("Email già esistente");
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
		return dataDiNascita;
	}

	public void setDataDiNascita(Date birthDay) {
		this.dataDiNascita = birthDay;
	}

	public String getRegisterErrore() {
		return registrazioneSbagliata;
	}

	public void setRegisterErrore(String registerError) {
		this.registrazioneSbagliata = registerError;
	}



	public Calendar getDataDiRegistrazione() {
		return dataDiRegistrazione;
	}

	public void setDataDiRegistrazione(Calendar dataDiRegistrazione) {
		this.dataDiRegistrazione = dataDiRegistrazione;
	}

	public SessioneCliente getSession() {
		return sessione;
	}

	public void setSession(SessioneCliente session) {
		this.sessione = session;
	}
	
	
}