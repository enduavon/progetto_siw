package it.uniroma3.cikmed.facade;


import it.uniroma3.cikmed.model.Prodotto;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless (name="pFacade")
public class ProdottoFacade {
	
	@PersistenceContext (unitName="progetto")
	private EntityManager em;
	
	
	public Prodotto creaProdotto(String nome, String codice, String descrizione, Float prezzo, 
			int quantita) {

		Prodotto p = new Prodotto(nome, codice, descrizione, prezzo, quantita);
		em.persist(p);
		return p;
	}
	

	public List<Prodotto> getCatalogoProdotti() {
		
		try {
			TypedQuery<Prodotto> q = em.createQuery("SELECT p FROM Prodotto p", Prodotto.class);
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
			TypedQuery<Prodotto> q = em.createQuery("SELECT p FROM Prodotto p WHERE p.id =: id", Prodotto.class);
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
	
	public void decreaseQuantitaProdotto (Prodotto p, int quantita) {
		p.setQuantita(p.getQuantita()-quantita);
		em.merge(p);
	}
	
	public void deleteProdotto (Prodotto p) {
		em.remove(p);
	}
	
	public void deleteProdottoByID (long id) {
		Prodotto p = getProdottoByID(id);
		deleteProdotto(p);
	}
	
}
package it.uniroma3.cikmed.facade;


import it.uniroma3.cikmed.model.Prodotto;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless (name="pFacade")
public class ProdottoFacade {
	
	@PersistenceContext (unitName="progetto")
	private EntityManager em;
	
	
	public Prodotto creaProdotto(String nome, String codice, String descrizione, Float prezzo, 
			int quantita) {

		Prodotto p = new Prodotto(nome, codice, descrizione, prezzo, quantita);
		em.persist(p);
		return p;
	}
	

	public List<Prodotto> getCatalogoProdotti() {
		
		try {
			TypedQuery<Prodotto> q = em.createQuery("SELECT p FROM Prodotto p", Prodotto.class);
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
			TypedQuery<Prodotto> q = em.createQuery("SELECT p FROM Prodotto p WHERE p.id =: id", Prodotto.class);
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
	
	public void decreaseQuantitaProdotto(Prodotto p, int quantita) {
		p.setQuantita(p.getQuantita()-quantita);
		em.merge(p);
	}
	
	public void deleteProdotto (Prodotto p) {
		em.remove(p);
	}
	
	public void deleteProdottoByID (long id) {
		Prodotto p = getProdottoByID(id);
		deleteProdotto(p);
	}
	
}
