package it.uniroma3.cikmed.facade;




import it.uniroma3.cikmed.model.Fornitore;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class FornitoreFacade {

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


	public Fornitore creaFornitore(String iva, String indirizzo, int telefono, String email) {
		this.openEM();

		Fornitore f = new Fornitore(iva, indirizzo, telefono, email);
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.persist(f);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			f = null;

		} finally {
			this.closeEM();
		}
		
		return f;
	}

	public List<Fornitore> getAllIFornitori() {
		this.openEM();


		try {
			String query = "SELECT f" +
					"FROM Fornitore f";
			TypedQuery<Fornitore> q = em.createQuery(query, Fornitore.class);
			return q.getResultList();

		} 
		catch (Exception e) {
			String q = "la lista dei fornitori è vuota";
			System.out.println(q);
			return null;

		}

		finally {
			this.closeEM();
		}


	}

	public Fornitore getFornitoreByID(long id) {
		this.openEM();


		try {
			String query = "SELECT f" +
					"FROM Fornitore f" +
					"WHERE f.id =: id";
			TypedQuery<Fornitore> q = em.createQuery(query, Fornitore.class);
			q.setParameter("id", id);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "il fornitore selezionato non esiste";
			System.out.println(q);
			return null;

		}

		finally {
			this.closeEM();
		}


	}
}
