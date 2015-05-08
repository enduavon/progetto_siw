package it.uniroma3.cikmed.facade;



import it.uniroma3.cikmed.model.Cliente;

import java.util.Calendar;
import java.util.List;










import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ClienteFacade {

	private EntityManager em;
	private EntityManagerFactory emf;

	public void openEM() {
		this.emf = Persistence.createEntityManagerFactory("progetto-unit");
		this.em = emf.createEntityManager();
	}

	private void closeEM() {
		this.em.close();
		this.emf.close();

	}


	public void creaCliente(String nome,String nickname,String password, String cognome, 
			Calendar dataDiNascita, Calendar dataDiRegistrazione,
			String indirizzo, String email) {
		this.openEM();

		Cliente c = new Cliente( nome,nickname, password, cognome, dataDiNascita, 
									dataDiRegistrazione,
									indirizzo, email);
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.persist(c);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			c = null;

		} finally {
			this.closeEM();
		}

	}

	public List<Cliente> getTuttiClienti() {
		this.openEM();


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

		finally {
			this.closeEM();
		}


	}

	public Cliente getClienteByID(long id) {
		this.openEM();


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

		finally {
			this.closeEM();
		}


	}
}
