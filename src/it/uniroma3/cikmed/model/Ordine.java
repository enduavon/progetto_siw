package it.uniroma3.cikmed.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


import java.util.List;






import java.util.TimeZone;

import javax.persistence.CascadeType;
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

public class Ordine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	@Column (nullable = false)
	private String stato;

	@Column (nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataApertura;

	@Column (nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataChiusura;

	@Column (nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataEvasione;
	
	@Column (nullable = false)
	private float prezzoTotale;


	/*
	 * è la Owning di customer
	 */
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name = "cliente_id") 
	private Cliente cliente;
	/*
	 * forziamo la creazione della foreign key
	 * nella tabella della parte proprietaria, questo perchè
	 * jpa genera (anche in una relazione 1-molti e non solo in quelle
	 * molti-molti) delle tabelle di join, inutili come ben sappiamo.
	 * Per evitare si crea la foreign key nella owning side specificando
	 * il nome della colonna di join nella entità della parte inversa
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordine")
	private List<RigaOrdine> righeOrdine;


	public Ordine (Calendar dataApertura, Cliente cliente) {
		this.dataApertura = dataApertura;
		this.cliente = cliente;
		this.stato = "aperto"; //Quando si crea un nuovo ordine, il suo stato corrente è "aperto"
		this.righeOrdine = new ArrayList<RigaOrdine>(); 
		this.prezzoTotale = 0;
	}
	
	
	/*
	 * GETTERS & SETTERS
	 */
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public void chiudiOrdine() {
		this.stato = "chiuso";
		this.dataChiusura = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
	}
	
	public void evadiOrdine() {
		this.stato = "evaso";
		this.dataEvasione = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
	}
	
	public Calendar getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Calendar oa) {
		this.dataApertura = oa;
	}

	public Calendar getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(Calendar oc) {
		this.dataChiusura = oc;
	}

	public Calendar getDataEvasione() {
		return dataEvasione;
	}

	public void setDataEvasione(Calendar oe) {
		this.dataEvasione = oe;
	}
	
		
	public float getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(float prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente c) {
		this.cliente = c;
	}

	public List<RigaOrdine> getRigheOrdine() {
		return righeOrdine;
	}

	public void setRigheOrdine(List<RigaOrdine> ro) {
		this.righeOrdine = ro;
	}
}
