package it.uniroma3.cikmed.facade;


import it.uniroma3.cikmed.model.Ordine;
import it.uniroma3.cikmed.model.Prodotto;
import it.uniroma3.cikmed.model.RigaOrdine;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless (name="rigaordFacade")
public class RigaOrdineFacade {
	
	@PersistenceContext (unitName="progetto")
	private EntityManager em;
	
	
	public RigaOrdine creaRigaOrdine(Ordine o, Prodotto p, Float prezzo, int quantita) {

		RigaOrdine ro = new RigaOrdine(p, p.getPrezzo(), p.getQuantita());
		o.addRigaOrdine(ro);
		em.persist(ro);	
		em.merge(o);
		return ro;
	}
	
	public RigaOrdine getRigaOrdineByID(long id) {
		
		try {
			TypedQuery<RigaOrdine> q = em.createQuery("SELECT ro FROM RigaOrdine ro WHERE ro.id =: id", RigaOrdine.class);
			q.setParameter("id", id);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "l'ordine selezionato non esiste";
			System.out.println(q);
			return null;

		}
	}
	
	public void updateRigaOrdine (RigaOrdine ro) {
		em.merge(ro);
	}

	public void deleteRigaOrdine (RigaOrdine ro) {
		em.remove(ro);
	}
	
}
