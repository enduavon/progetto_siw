//package it.uniroma3.cikmed.beanController;
//
//
//import java.util.Calendar;
//import java.util.List;
//
//
//
//import it.uniroma3.cikmed.facade.ClienteFacade;
//import it.uniroma3.cikmed.model.Cliente;
//
//
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.RequestScoped;
//
//
//
//
//@ManagedBean (name="clienteController")
//@RequestScoped
//public class ClienteController {
//
//	@ManagedProperty(value="#{param.id}")
//	
//	private String nome;
//	private String cognome;
//	private String nickname;
//	private String password;
//	private String email;
//	private Calendar dataNascita;
//	
//	//private boolean isLogged;
//
//	private Cliente clienteCorrente;
//	private List<Cliente> clientiRegistrati; //tutti gli utenti registrati
//
//	@EJB (beanName="clienteFacade")
//	private ClienteFacade clienteFacade;
//
//
//
//
//	public String findCliente(Long id) {
//		this.clienteCorrente = clienteFacade.getClienteByID(id);
//		return "showCliente";
//	}
//	
//	public String riepilogoCliente() {
//		return "showCliente";
//	}
//
//
//	public String listClienti() {
//		this.clientiRegistrati = clienteFacade.getTuttiClienti();
//		return "showClienti"; 
//	}
//
//	
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getNickname() {
//		return nickname;
//	}
//
//	public void setNickname(String nickname) {
//		this.nickname = nickname;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public Cliente getCliente() {
//		return clienteCorrente;
//	}
//
//	public void setCliente(Cliente cliente) {
//		this.clienteCorrente = cliente;
//	}
//
//	public List<Cliente> getClienti() {
//		return clientiRegistrati;
//	}
//
//	public void setClienti(List<Cliente> clienti) {
//		this.clientiRegistrati = clienti;
//	}
//
//	public ClienteFacade getClienteFacade() {
//		return clienteFacade;
//	}
//
//	public void setClienteFacade(ClienteFacade clienteFacade) {
//		this.clienteFacade = clienteFacade;
//	}
//
//	public Calendar getDataNascita() {
//		return dataNascita;
//	}
//
//	public void setDataNascita(Calendar dataNascita) {
//		this.dataNascita = dataNascita;
//	}
//
//	public String getCognome() {
//		return cognome;
//	}
//
//	public void setCognome(String cognome) {
//		this.cognome = cognome;
//	}
//
//}
//				