package it.uniroma3.cikmed.controller;

import java.util.List;

import it.uniroma3.cikmed.facade.ClienteFacade;
import it.uniroma3.cikmed.model.Cliente;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean (name="clienteController")
public class ClienteController {
	
	@ManagedProperty(value="#{param.id}")
	private Long id;
	private String nome;
	private String nickname;
	private String password;
	private String email;
	
	
	private Cliente cliente;
	private List<Cliente> clienti;
	
	@EJB (beanName="cFacade")
	private ClienteFacade clienteFacade;
	
	
	//creare metodi per login e logout cliente
	
	
	public String listClienti() {
		this.clienti = clienteFacade.getTuttiClienti();
		return "showClienti"; 
	}

	public String findCliente(Long id) {
		this.cliente = clienteFacade.getClienteByID(id);
		return "showCliente";
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
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClienti() {
		return clienti;
	}

	public void setClienti(List<Cliente> clienti) {
		this.clienti = clienti;
	}
	
	public ClienteFacade getClienteFacade() {
		return clienteFacade;
	}

	public void setClienteFacade(ClienteFacade clienteFacade) {
		this.clienteFacade = clienteFacade;
	}

	
}