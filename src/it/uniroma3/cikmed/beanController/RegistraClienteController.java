package it.uniroma3.cikmed.beanController;

import it.uniroma3.cikmed.facade.ClienteFacade;
import it.uniroma3.cikmed.model.Cliente;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="registraCliente")
@RequestScoped
public class RegistraClienteController {

	@EJB(beanName="clienteFacade")
	private ClienteFacade facade;

	@ManagedProperty(value="#{param.id}")
	private String email;
	private String nickname;
	private String password;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private Calendar dataDiRegistrazione;

	private String errore;
	private String confermaPassword;
	private Cliente clienteRegistrato;

	private Long id;
	
	public String registraCliente() {
		try {
			if(facade.verificaEmail(email)!=true) {

				clienteRegistrato = facade.creaCliente(nome, nickname, password, cognome, dataDiNascita, email);
				facade.updateCliente(clienteRegistrato);
				return "mostraCliente"; } }
		catch (Exception e) {
			if(e.getClass().getName().equals("javax.ejb.EJBTransactionRolledbackException")){
				setErrore("Email già esistente. Utilizza un altro indirizzo email per registrarti.");
				return "registrazioneCliente"; }
			else {
				setErrore("Impossibile registrarsi");
				return "registrazioneCliente";
			} }
		return "registrazioneCliente";
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

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date birthDay) {
		this.dataDiNascita = birthDay;
	}

	public String getErrore() {
		return errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}


	public Calendar getDataDiRegistrazione() {
		return dataDiRegistrazione;
	}

	public void setDataDiRegistrazione(Calendar dataDiRegistrazione) {
		this.dataDiRegistrazione = dataDiRegistrazione;
	}



	public Cliente getClienteRegistrato() {
		return clienteRegistrato;
	}

	public void setClienteRegistrato(Cliente clienteRegistrato) {
		this.clienteRegistrato = clienteRegistrato;
	}


	public String getConfermaPassword() {
		return confermaPassword;
	}


	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}