package it.uniroma3.cikmed.facade;


import it.uniroma3.cikmed.model.Cliente;
import it.uniroma3.cikmed.model.Indirizzo;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless (name="cFacade")
public class ClienteFacade {

	@PersistenceContext (unitName="progetto")
	private EntityManager em;

	public ClienteFacade() {
		super();
	}


	public Cliente creaCliente(String nome,String nickname,String password, String cognome, 
			Calendar dataDiNascita, Calendar dataDiRegistrazione,
			Indirizzo indirizzo, String email) {

		Cliente c = new Cliente(nome, cognome, nickname, password, dataDiNascita, 
				dataDiRegistrazione, indirizzo, email);
		em.persist(c);		
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

	public Cliente getClienteByEmail(String email) {

		try {
			String query = "SELECT c" +
					"FROM Cliente c" +
					"WHERE c.email =: email";
			TypedQuery<Cliente> q = em.createQuery(query, Cliente.class);
			q.setParameter("email", email);
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
