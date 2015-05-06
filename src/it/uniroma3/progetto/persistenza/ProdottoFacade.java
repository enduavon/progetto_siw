package it.uniroma3.progetto.persistenza;



import java.util.List;







import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProdottoFacade {

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


	public void creaProdotto(String nome, String codice, String descrizione, Float prezzo, int quantità) {
		this.openEM();

		Prodotto p = new Prodotto(nome, codice, descrizione, prezzo, quantità);
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.persist(p);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			p = null;

		} finally {
			this.closeEM();
		}

	}

	public List<Prodotto> getCatalogoProdotti() {
		this.openEM();


		try {
			String query = "SELECT p" +
					"FROM Prodotto p";
			TypedQuery<Prodotto> q = em.createQuery(query, Prodotto.class);
			return q.getResultList();

		} 
		catch (Exception e) {
			String q = "la lista è vuota";
			System.out.println(q);
			return null;

		}

		finally {
			this.closeEM();
		}


	}

	public Prodotto getProdottoByID(long id) {
		this.openEM();


		try {
			String query = "SELECT p" +
					"FROM Prodotto p" +
					"WHERE p.id =: id";
			TypedQuery<Prodotto> q = em.createQuery(query, Prodotto.class);
			q.setParameter("id", id);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "il prodotto non è presente";
			System.out.println(q);
			return null;

		}

		finally {
			this.closeEM();
		}


	}
}
