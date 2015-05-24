package it.uniroma3.cikmed.controller;


import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import it.uniroma3.cikmed.facade.ClienteFacade;
import it.uniroma3.cikmed.model.Cliente;
import it.uniroma3.cikmed.model.Indirizzo;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


@ManagedBean (name="clienteController")
public class ClienteController {

	@ManagedProperty(value="#{param.id}")
	private Long id;
	private String nome;
	private String cognome;
	private String nickname;
	private String password;
	private String email;
	private Calendar dataRegistrazione;
	private Calendar dataNascita;
	private Indirizzo indirizzo;
	//private boolean isLogged;

	private Cliente clienteCorrente;
	private List<Cliente> clientiRegistrati; //tutti gli utenti registrati

	@EJB (beanName="cFacade")
	private ClienteFacade clienteFacade;


//	public String registraCliente() {
//		try{
//			/*Genera automaticamente la data di oggi */
//			this.dataRegistrazione = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
//			this.clienteCorrente = clienteFacade.creaCliente(nome, nickname, password, cognome, dataNascita, 
//					dataRegistrazione, indirizzo, email);
//			return "registrazioneEffettuata";
//		}catch(Exception e){
//			/*Utente già registrato*/
//			this.resetCliente();
//			FacesContext.getCurrentInstance().addMessage("registrazioneCliente:signin come Cliente", new FacesMessage("Utente già registrato!"));
//			return "registrazioneCliente";
//		}
//	}


//	private void resetCliente(){
//		this.nome = null;
//		this.cognome = null;
//		this.password = null;
//		this.email = null;
//		this.nickname = null;
//		this.indirizzo = null;
//		this.dataNascita = null;
//		this.dataRegistrazione = null;
//	}

//	public String loginCliente() {
//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("AmministratoreController");
//		FacesMessage msg = new FacesMessage("Login Errato! Email o password non inseriti correttamente!");
//		
//		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//		
//		try{
//			Cliente client = clienteFacade.getClienteByEmail(email);
//			if (client.verificaPassword(this.password)) {
//				setCliente(client);
//				return "paginaCliente";
//			}
//
//			else{
//				// Password Errata
//				FacesContext.getCurrentInstance().addMessage("loginCliente:error", msg);
//				return "login";
//			}
//		}
//		catch (Exception e) {
//			// Cliente non trovato
//			FacesContext.getCurrentInstance().addMessage("loginCliente:error", msg);
//			return "login";
//		}
//	}


//	public String logoutCliente() {
// FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
// /*TODO*/return "";
//	}

	public String findCliente(Long id) {
		this.clienteCorrente = clienteFacade.getClienteByID(id);
		return "showCliente";
	}
	
	public String riepilogoCliente() {
		return "showCliente";
	}


	public String listClienti() {
		this.clientiRegistrati = clienteFacade.getTuttiClienti();
		return "showClienti"; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente getCliente() {
		return clienteCorrente;
	}

	public void setCliente(Cliente cliente) {
		this.clienteCorrente = cliente;
	}

	public List<Cliente> getClienti() {
		return clientiRegistrati;
	}

	public void setClienti(List<Cliente> clienti) {
		this.clientiRegistrati = clienti;
	}

	public ClienteFacade getClienteFacade() {
		return clienteFacade;
	}

	public void setClienteFacade(ClienteFacade clienteFacade) {
		this.clienteFacade = clienteFacade;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cognome="
				+ cognome + ", nickname=" + nickname + ", password=" + password
				+ ", email=" + email + ", dataRegistrazione="
				+ dataRegistrazione + ", dataNascita=" + dataNascita
				+ ", indirizzo=" + indirizzo + "]";
	}
}