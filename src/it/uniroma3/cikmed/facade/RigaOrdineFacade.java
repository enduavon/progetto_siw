package it.uniroma3.cikmed.facade;


import java.util.List;

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
	
	
	public RigaOrdine creaRigaOrdine(Prodotto p, Float prezzo, int quantita, Ordine ordine) {

		RigaOrdine ro = new RigaOrdine(p, p.getPrezzo(), quantita, ordine);
		em.persist(ro);	
		return ro;
	}
	
	public RigaOrdine getRigaOrdineByID(long id) {
		
		try {
			TypedQuery<RigaOrdine> q = em.createQuery("SELECT ro FROM RigaOrdine ro WHERE ro.id = :id", RigaOrdine.class);
			q.setParameter("id", id);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "l'ordine selezionato non esiste";
			System.out.println(q);
			return null;

		}
	}
	
	public List<RigaOrdine> getRigheOrdineByProdotto(Prodotto prodotto) {

		try {
			TypedQuery<RigaOrdine> q = em.createQuery("SELECT ro FROM RigaOrdine ro WHERE ro.prodotto = :prodotto", RigaOrdine.class);
			q.setParameter("prodotto", prodotto);
			return q.getResultList();

		} 
		catch (Exception e) {
			String q = "non esistono righe ordine relative al prodotto "  +prodotto+  "";
			System.out.println(q);
			return null;

		}
	}
	
	
	public RigaOrdine getRigaOrdineProdottoByOrdine(Prodotto prodotto, Ordine ordine) {

		try {
			TypedQuery<RigaOrdine> q = em.createQuery("SELECT ro FROM RigaOrdine ro WHERE ro.prodotto = :prodotto AND"
					+ "ro.ordine.orders_id = :ordine", RigaOrdine.class);
			q.setParameter("prodotto", prodotto);
			q.setParameter("ordine", ordine);
			return q.getSingleResult(); 

		} 
		catch (Exception e) {
			String q = "Nell'ordine n° " +ordine+ " non vi sono righe relative al prodotto "  +prodotto+  "";
			System.out.println(q);
			return null;

		}
	}
	
	public void updateRigaOrdine (RigaOrdine ro) {
		em.merge(ro);
	}
	
	public void increaseQuantitàRigaOrdine (RigaOrdine ro, int quantità) {
		ro.setQuantita(ro.getQuantita()-quantità);
		em.merge(ro);
	}

	public void deleteRigaOrdine (RigaOrdine ro) {
		em.remove(ro);
	}
	
}
