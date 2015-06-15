package it.uniroma3.cikmed.facade;

import it.uniroma3.cikmed.model.Indirizzo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless(name="indFacade")
public class IndirizzoFacade {
	
	@PersistenceContext (unitName="progetto")
	private EntityManager em;
	
	
	public Indirizzo creaIndirizzo(String via, String citta, String nazione, String codicePostale, 
			String stato) {

		Indirizzo indirizzo = new Indirizzo(via, citta, nazione, codicePostale, stato);
		em.persist(indirizzo);	
		return indirizzo;

	}
	
	
	public Indirizzo getIndirizzoByID(long id) {
		
		try {
			TypedQuery<Indirizzo> q = em.createQuery("SELECT ind FROM Indirizzo ind WHERE ind.id = :id", Indirizzo.class);
			q.setParameter("id", id);
			return q.getSingleResult();

		} 
		catch (Exception e) {
			String q = "L'indirizzo con id " +id+ " non esiste";
			System.out.println(q);
			return null;

		}

	} 
	
	
	public void deleteIndirizzo(Indirizzo indirizzo) {
		indirizzo = em.find(Indirizzo.class, indirizzo);
		em.remove(indirizzo);		
	}
	
	public void updateIndirizzo(Indirizzo indirizzo) {
		em.merge(indirizzo);
	}

}
