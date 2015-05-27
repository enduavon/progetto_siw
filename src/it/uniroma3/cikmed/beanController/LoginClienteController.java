package it.uniroma3.cikmed.beanController;

import it.uniroma3.cikmed.beanController.sessioni.SessioneCliente;
import it.uniroma3.cikmed.facade.ClienteFacade;
import it.uniroma3.cikmed.model.Cliente;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean (name="loginCliente")
@RequestScoped
public class LoginClienteController {


	@EJB (beanName="clienteFacade")
	private ClienteFacade clienteFacade;

//	@ManagedProperty(value="#{sessioneCliente}")
	private SessioneCliente sessione;
	
	private String email;
	private String password;
	private String loginErrore;

	public String loginCliente() {
		try {
			Cliente cl = clienteFacade.trovaClienteByEmailPwd(email, password);
			sessione.setCliente(cl);
		} 
		catch (Exception e) {
			loginErrore = "email o password non validi";
			return "loginCliente";
		}
		return "mostraCliente";
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
	
	public String getLoginErrore() {
		return loginErrore;
	}
	public void setLoginErrore(String loginError) {
		this.loginErrore = loginError;
	}

	public SessioneCliente getSession() {
		return sessione;
	}

	public void setSession(SessioneCliente session) {
		this.sessione = session;
	}


}
