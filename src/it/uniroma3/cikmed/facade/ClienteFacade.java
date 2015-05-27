package it.uniroma3.cikmed.facade;


import it.uniroma3.cikmed.model.Cliente;



import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless (name="clienteFacade")
public class ClienteFacade {

	@PersistenceContext (unitName="progetto")
	private EntityManager em;

	public ClienteFacade() {
		super();
	}


	public Cliente creaCliente(String nome,String nickname,String password, String cognome, 
			Date dataDiNascita, Calendar dataDiRegistrazione, String email) {

		Cliente c = new Cliente(nome, nickname, password, cognome, dataDiNascita, dataDiRegistrazione, email);
		this.em.persist(c);		
		return c;
	}


	public List<Cliente> getTuttiClienti() {

		try {
			String query = "SELECT c" +
					"FROM Cliente c";
			TypedQuery<Cliente> q = em.createQuery(query, Cliente.class);
			return q.getResultList();
		} 

		catch (Exception e) {
			String q = "la lista dei clienti è vuota";
			System.out.println(q);
			return null;

		}

	}
	
	
	public Cliente getClienteByID(long id) {

		try {
			String query = "SELECT c" +
					"FROM Cliente c" +
					"WHERE c.id =: id";
			TypedQuery<Cliente> q = em.createQuery(query, Cliente.class);
			q.setParameter("id", id);
			return q.getSingleResult();
		} 

		catch (Exception e) {
			String q = "il cliente selezionato non esiste";
			System.out.println(q);
			return null;

		}
	}

	public boolean getClienteByEmail(String email) {

		try {
			String query = "SELECT c" +
					"FROM Cliente c" +
					"WHERE c.email =: email";
			TypedQuery<Cliente> q = em.createQuery(query, Cliente.class);
			q.setParameter("email", email);
			return true;
		} 

		catch (Exception e) {
			String q = "il cliente con la mail"  +email+  "non esiste";
			System.out.println(q);
			return false;

		}
	}

	public Cliente trovaClienteByEmailPwd(String email, String password) {
		try {
			String query = "SELECT c" +
					"FROM Cliente c" +
					"WHERE c.email =: email" +
					"AND c.password =: password";
			TypedQuery<Cliente> q = em.createQuery(query, Cliente.class);
			q.setParameter("email", email);
			q.setParameter("password", password);
			return q.getSingleResult();
		} 

		catch (Exception e) {
			String q = "il cliente selezionato non esiste";
			System.out.println(q);
			return null;

		}
	}

	public void updateCliente (Cliente c) {
		em.merge(c);
	}

	public void deleteCliente (Cliente c) {
		em.remove(c);
	}

	public void deleteClienteById (long id) {
		Cliente c = em.find(Cliente.class, id);
		deleteCliente(c);
	}






}
