package it.uniroma3.cikmed.facade;


import it.uniroma3.cikmed.model.Cliente;
import it.uniroma3.cikmed.model.Ordine;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless (name="ordFacade")
public class OrdineFacade {
	
	@PersistenceContext (unitName="progetto")
	private EntityManager em;
	
	
	public Ordine creaOrdine(Calendar dataApertura, Cliente cliente) {
		
		Ordine o = new Ordine(dataApertura, cliente);
		em.persist(o);	
		return o;

	}

	public List<Ordine> getListaOrdiniByStato(String stato) {

		try {
			TypedQuery<Ordine> q = em.createQuery("SELECT ord FROM Ordine ord WHERE ord.stato = :stato", Ordine.class);
			q.setParameter("stato", stato);
			return q.getResultList();

		} 
		catch (Exception e) {
			String q = "la lista degli ordini chiusi è vuota";
			System.out.println(q);
			return null;

		}

	}

	public Ordine getOrdineByID(long id) {
	
		try {
			TypedQuery<Ordine> q = em.createQuery("SELECT ord FROM Ordine ord WHERE ord.id = :id", Ordine.class);
			q.setParameter("id", id);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "l'ordine selezionato non esiste";
			System.out.println(q);
			return null;

		}

	} 
	
	//non funziona questo o il passaggio di parametri di #loginCliente.clienteLoggato?
	public List<Ordine> getOrdiniCliente (Cliente cliente) {
		
		try {
			TypedQuery<Ordine> q = em.createQuery("SELECT ord FROM Ordine ord WHERE ord.cliente = :cliente", Ordine.class);
			q.setParameter("cliente", cliente);
			return q.getResultList();

		} 
		catch (Exception e) {
			String q = "Il cliente " +cliente.getNickname()+ " non ha creato degli ordini";
			System.out.println(q);
			return null;

		}

	}
	
	
	public List<Ordine> getStatoOrdineByCliente (String stato, Cliente cliente) {   
		try {
			TypedQuery<Ordine> q = em.createQuery("SELECT ord FROM Ordine ord WHERE ord.stato = :stato AND ord.cliente = :cliente", Ordine.class);
			q.setParameter("stato", stato);
			q.setParameter("cliente", cliente);
			return q.getResultList();

		} 
		catch (Exception e) {
			String q = "Il cliente " +cliente.getId()+ " al momento non ha ordini nello stato " +stato+ "";
			System.out.println(q);
			return null;

		}
	}
	
	public Ordine getOrdineApertoByCliente (String stato, Cliente cliente) {   
		try {
			TypedQuery<Ordine> q = em.createQuery("SELECT ord FROM Ordine ord WHERE ord.stato = :stato AND ord.cliente = :cliente", Ordine.class);
			q.setParameter("stato", stato);
			q.setParameter("cliente", cliente);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "Il cliente " +cliente.getId()+ " al momento non ha un ordine aperto";
			System.out.println(q);
			return null;

		}
	}
	
	public boolean checkOrdineChiuso (Ordine ordine, String stato) {
		
		try {
			TypedQuery<Ordine> q = em.createQuery("SELECT ord FROM Ordine ord WHERE ord.stato = :stato", Ordine.class);
			q.setParameter("stato", stato);
			if (q.getResultList().contains(ordine)) { 
				return true;
			}

			return false;

		} 
		catch (Exception e) {
			String q = "L'ordine non è ancora chiuso";
			System.out.println(q);
			return false;
		}
	}
	
	
	public void updateOrdine (Ordine o) {
		em.merge(o);
	}
	
	public void closeOrdineByID(Long id) {
		Ordine o = em.find(Ordine.class, id);
		o.chiudiOrdine();
		updateOrdine(o);
	}
	
	public void evadeOrdine(Ordine o) {
		o.evadiOrdine();
		updateOrdine(o);
	}
	
	public void increasePrezzoTotale(Ordine o, float prezzo) {
		o.setPrezzoTotale(o.getPrezzoTotale()+prezzo);
		updateOrdine(o);
	}
	
	public void decreasePrezzoTotale(Ordine o, float prezzo) {
		o.setPrezzoTotale(o.getPrezzoTotale()-prezzo);
		updateOrdine(o);
	}

	public void deleteOrdine (Ordine o) {
		o = em.find(Ordine.class, o.getId());
		em.remove(o);
	}

	public void deleteOrdineByID (long id) {
		Ordine o = getOrdineByID(id);
		deleteOrdine(o);
	}
	
	public void deleteOrdiniCliente (Cliente cliente) {
		Cliente c = em.find(Cliente.class, cliente);
		List<Ordine> listaOrdini= c.getOrdini();
		em.remove(listaOrdini);
	}
}
