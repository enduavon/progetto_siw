package it.uniroma3.cikmed.facade;


import it.uniroma3.cikmed.model.Prodotto;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless (name="pFacade")
public class ProdottoFacade {
	
	@PersistenceContext (unitName="progetto-unit")
	private EntityManager em;
	
	
	public Prodotto creaProdotto(String nome, String codice, String descrizione, Float prezzo, 
			int quantità) {

		Prodotto p = new Prodotto(nome, codice, descrizione, prezzo, quantità);
		em.persist(p);
		return p;
	}

	public List<Prodotto> getCatalogoProdotti() {
		
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

	}

	public Prodotto getProdottoByID(long id) {
	
		try {
			String query = "SELECT p" +
					"FROM Prodotto p" +
					"WHERE p.id =: id";
			TypedQuery<Prodotto> q = em.createQuery(query, Prodotto.class);
			q.setParameter("id", id);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "il prodotto con id" +id+ "non è presente";
			System.out.println(q);
			return null;

		}
		
	}
	
	public void updateProdotto (Prodotto p) {
	em.merge(p);
	}
	
	public void deleteProdotto (Prodotto p) {
		em.remove(p);
	}
	
	public void deleteProdottoById (long id) {
		Prodotto p = em.find(Prodotto.class, id);
		deleteProdotto(p);
	}
	
//	public void deleteCatalogoProdotti () {
//		
//	}

}
