package it.uniroma3.cikmed.beanController;

import it.uniroma3.cikmed.facade.ClienteFacade;
import it.uniroma3.cikmed.model.Cliente;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean (name="loginCliente")
@SessionScoped
public class LoginClienteController {


	@EJB (beanName="clienteFacade")
	private ClienteFacade clienteFacade;
	
	private String email;
	private String password;
	private String messaggio;
	private Cliente clienteLoggato;


	public String loginCliente() {
		try {
			clienteLoggato = clienteFacade.trovaClienteByEmailPwd(email, password);
			setMessaggio("Perfetto, login effettuato con successo.");
			return "index"; //bisogna fare una pagina apposita senza pulsanti di login
		} 
		catch (Exception e) {
			setMessaggio("email o password non validi");
			return "loginCliente";
		}
	}
	
	public String logOut() {
		clienteLoggato = null;
		return "index";
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
	
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public Cliente getClienteLoggato() {
		return clienteLoggato;
	}

	public void setClienteLoggato(Cliente clienteLoggato) {
		this.clienteLoggato = clienteLoggato;
	}
	
}
