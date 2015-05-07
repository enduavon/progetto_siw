package it.uniroma3.progetto.persistenza;


import java.util.ArrayList;
import java.util.Calendar;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "orders")

public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	@Column
	private boolean chiuso; //Stato chiuso dell'ordine

	@Column
	private boolean evaso; //Stato evaso dell'ordine

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ordineAperto;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ordineChiuso;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ordineEvaso;


	/*
	 * è la Owning di customer
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;
	/*
	 * forziamo la creazione della foreign key
	 * nella tabella della parte proprietaria, questo perchè
	 * jpa genera (anche in una relazione 1-molti e non solo in quelle
	 * molti-molti) delle tabelle di join, inutili come ben sappiamo.
	 * Per evitare si crea la foreign key nella owning side specificando
	 * il nome della colonna di join nella entità della parte inversa
	 */
	@OneToMany
	@JoinColumn(name = "orders_id")
	private List<RigaOrdine> righeOrdine;

	public Ordine() {

	}

	public Ordine (Calendar ordineAperto, Cliente cliente) {
		this.ordineAperto = ordineAperto;
		this.cliente = cliente;
		this.chiuso = false; //Alla creazione l'ordine è aperto
		this.righeOrdine = new ArrayList<RigaOrdine>();
	}


	public void addRigaOrdine(RigaOrdine r) {
		this.righeOrdine.add(r);
	}
	
	public void rimuoviRigaOrdine(RigaOrdine r) {
		this.righeOrdine.remove(r);
	}
	
	public RigaOrdine getRigaOrdineById(long idRigaOrdine) {
		RigaOrdine ro = null;
		for(RigaOrdine r : this.righeOrdine) {
			if(r.getID().equals(idRigaOrdine))
				ro = r;
		}
	return ro;
	}
	
	
	public RigaOrdine getRigaOrdineByProdotto(Prodotto p) {
		RigaOrdine ro = null;
		for(RigaOrdine r : this.righeOrdine){
			if(r.getProdottoDellaRigaOrdine().getId().equals
					(p.getId()))
				ro = r;
		}
		return ro;
	}


	public Long getId() {
		return id;
	}

	public boolean isChiuso() {
		return chiuso;
	}

	public void setChiuso() {
		this.chiuso = true;
	}
	
	public boolean isEvaso() {
		return evaso;
	}

	public void setEvaso() {
		this.evaso = true;
	}

	public Calendar getDataAperturaOrdine() {
		return ordineAperto;
	}

	public void setDataAperturaOrdine(Calendar oa) {
		this.ordineAperto = oa;
	}

	public Calendar getDataOrdineChiuso() {
		return ordineChiuso;
	}

	public void setDataOrdineChiuso(Calendar oc) {
		this.ordineChiuso = oc;
	}

	public Calendar getDataOrdineEvaso() {
		return ordineEvaso;
	}

	public void setDataOrdineEvaso(Calendar oe) {
		this.ordineEvaso = oe;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente c) {
		this.cliente = c;
	}

	public List<RigaOrdine> getRigaOrdine() {
		return righeOrdine;
	}

	public void setRigaOrdine(List<RigaOrdine> ro) {
		this.righeOrdine = ro;
	}
	
	




}
