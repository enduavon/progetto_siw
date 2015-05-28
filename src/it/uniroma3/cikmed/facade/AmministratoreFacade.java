
package it.uniroma3.cikmed.facade;

import it.uniroma3.cikmed.model.Amministratore;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless (name="amministratoreFacade")
public class AmministratoreFacade {

	@PersistenceContext (unitName="progetto")
	private EntityManager em;

	public AmministratoreFacade() {
		super();
	}


	public Amministratore creaAdmin(String nickname, String password, String email) {

		Amministratore admin = new Amministratore(nickname, password, email);
		em.persist(admin);		
		return admin;
	}


	public List<Amministratore> getAllAdmins() {

		try {
			String query = "SELECT admin" +
					"FROM Amministratore admin";
			TypedQuery<Amministratore> q = em.createQuery(query, Amministratore.class);
			return q.getResultList();
		} 

		catch (Exception e) {
			String q = "la lista degli amministratori � vuota";
			System.out.println(q);
			return null;

		}

	}

	public Amministratore trovaAdminByEmailPwd(String email, String password)
			throws Exception {
		TypedQuery<Amministratore> query = em.createQuery(
				"SELECT a FROM Amministratore a where a.email =:email", Amministratore.class);
		query.setParameter("email", email);
		Amministratore admin = query.getSingleResult();
		if (admin == null) {
			throw new Exception();
		}
		admin.checkPassword(password);
		return admin;	
	}


	public Amministratore getAmministratoreByID(long id) {

		try {
			String query = "SELECT admin" +
					"FROM Amministratore admin" +
					"WHERE admin.id =: id";
			TypedQuery<Amministratore> q = em.createQuery(query, Amministratore.class);
			q.setParameter("id", id);
			return q.getSingleResult();
		} 

		catch (Exception e) {
			String q = "l'amministratore selezionato non esiste";
			System.out.println(q);
			return null;

		}
	}
	
	public boolean verificaEmail(String email) {
		TypedQuery<Amministratore> query = em.createQuery("SELECT a FROM Amministratore a where a.email =:email", Amministratore.class);
		query.setParameter("email", email);
		return query.getResultList().size() != 0;
	}

	public void updateAdmin (Amministratore admin) {
		em.merge(admin);
	}

	public void deleteAdmin (Amministratore admin) {
		em.remove(admin);
	}

	public void deleteAdminById (long id) {
		Amministratore admin = em.find(Amministratore.class, id);
		deleteAdmin(admin);
	}

}


