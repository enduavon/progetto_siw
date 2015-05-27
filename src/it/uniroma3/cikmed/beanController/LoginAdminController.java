package it.uniroma3.cikmed.beanController;



import it.uniroma3.cikmed.beanController.sessioni.SessioneAdmin;
import it.uniroma3.cikmed.facade.AmministratoreFacade;
import it.uniroma3.cikmed.model.Amministratore;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="loginAdmin", eager=true)
public class LoginAdminController {
	
		
		@EJB(beanName="amministratoreFacade")
		private AmministratoreFacade facade;
		
		@ManagedProperty(value="#{sessioneAdmin}")
		private SessioneAdmin session;
		
		
		private String email;
		private String password;
		
		private String loginErrore;
		
		public String loginAdmin() {
			try {
				Amministratore admin = facade.trovaAdminByEmailPwd(email, password);
				session.setAdmin(admin);
				
			} catch (Exception e) {
				loginErrore = "Email o password non valida";
				return "loginAdmin";
			}
			return "mostraAdmin";
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
		
		public String getLoginError() {
			return loginErrore;
		}
		public void setLoginError(String loginError) {
			this.loginErrore = loginError;
		}

		public SessioneAdmin getSession() {
			return session;
		}

		public void setSession(SessioneAdmin session) {
			this.session = session;
		}




	
}