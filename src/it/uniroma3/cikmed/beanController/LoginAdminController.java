package it.uniroma3.cikmed.beanController;


import it.uniroma3.cikmed.facade.AmministratoreFacade;
import it.uniroma3.cikmed.model.Amministratore;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="loginAdmin", eager=true)
@SessionScoped
public class LoginAdminController {
	
		
		@EJB(beanName="amministratoreFacade")
		private AmministratoreFacade facade;
		
		private String email;
		private String password;
		private Amministratore admin;
		
		private String messaggio;
		
		
		public String loginAdmin() {
			try {
				admin = facade.trovaAdminByEmailPwd(email, password);
				return "index"; //qui bisogna rimandare ad una pagina apposita, al contrario del cliente?
			} catch (Exception e) {
				setMessaggio("Email o password non valida");
				return "loginAdmin";
			}
		}
		
		public String logOut() {
			admin = null;
			return "/index?faces-redirect=true";
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


		public Amministratore getAdmin() {
			return admin;
		}
		
		public void setAdmin(Amministratore admin) {
			this.admin = admin;
		}

}