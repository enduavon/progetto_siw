package it.uniroma3.cikmed.facade;

import it.uniroma3.cikmed.model.Fornitore;
import it.uniroma3.cikmed.model.Indirizzo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless (name="fornFacade")
public class FornitoreFacade {
	
	@PersistenceContext (unitName="progetto")
	private EntityManager em;


	public Fornitore creaFornitore(String iva, int telefono, String email) {
	
		Fornitore f = new Fornitore(iva, telefono, email);
		em.persist(f);
		return f;
	}

	public List<Fornitore> getAllFornitori() {
		
		try {
			String query = "SELECT f FROM Fornitore f";
			TypedQuery<Fornitore> q = em.createQuery(query, Fornitore.class);
			return q.getResultList();

		} 
		catch (Exception e) {
			String q = "la lista dei fornitori è vuota";
			System.out.println(q);
			return null;

		}

	}

	public Fornitore getFornitoreByID(long id) {
		
		try {
			String query = "SELECT f FROM Fornitore f WHERE f.id = :id";
			TypedQuery<Fornitore> q = em.createQuery(query, Fornitore.class);
			q.setParameter("id", id);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "il fornitore con " +id+ " non esiste";
			System.out.println(q);
			return null;

		}

	}
	
	public void updateFornitore (Fornitore f) {
		em.merge(f);
	}
	
	public void addIndirizzo (Fornitore f, Indirizzo ind) {
		f.setIndirizzo(ind);
		updateFornitore(f);
	}

	public void deleteFornitore (Fornitore f) {
		em.remove(f);
	}

	public void deleteFornitoreById (long id) {
		Fornitore f = em.find(Fornitore.class, id);
		deleteFornitore(f);
	}
}
