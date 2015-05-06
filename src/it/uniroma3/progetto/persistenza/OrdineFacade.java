package it.uniroma3.progetto.persistenza;



import java.util.Calendar;
import java.util.List;











import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class OrdineFacade {

	private EntityManager em;
	private EntityManagerFactory emf;

	public void openEM() {
		this.emf = Persistence.createEntityManagerFactory("Progetto-unit");
		this.em = emf.createEntityManager();
	}

	private void closeEM() {
		this.em.close();
		this.emf.close();

	}


	public void creaOrdine(Calendar ordineAperto, Cliente cliente) {
		this.openEM();

		Ordine o = new Ordine(ordineAperto, cliente);
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.persist(o);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			o = null;

		} finally {
			this.closeEM();
		}

	}

	public List<Ordine> getTuttiGliOrdini() {
		this.openEM();


		try {
			String query = "SELECT ord" +
					"FROM Ordine ord";
			TypedQuery<Ordine> q = em.createQuery(query, Ordine.class);
			return q.getResultList();

		} 
		catch (Exception e) {
			String q = "la lista degli ordini è vuota";
			System.out.println(q);
			return null;

		}

		finally {
			this.closeEM();
		}


	}

	public Ordine getClienteByID(long id) {
		this.openEM();


		try {
			String query = "SELECT ord" +
					"FROM Ordine ord" +
					"WHERE ord.id =: id";
			TypedQuery<Ordine> q = em.createQuery(query, Ordine.class);
			q.setParameter("id", id);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "l'ordine selezionato non esiste";
			System.out.println(q);
			return null;

		}

		finally {
			this.closeEM();
		}


	}
}
