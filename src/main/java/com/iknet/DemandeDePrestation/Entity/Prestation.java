package com.iknet.DemandeDePrestation.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_de_prestation")
@DiscriminatorValue("Prestation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Prestation implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPrestation;
	private String adresseDePrestation;
	private Date dateDePrestation ;
	@Enumerated(EnumType.STRING)
	private EtatPrestation etatPrestation;
	@Enumerated(EnumType.STRING)
	private TypePrestation typePrestation;
	private float prixPrestation;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Client client;
	public Client getClient() {
		return client;
	}
	
	public Prestation(String adresseDePrestation, Date dateDePrivate, EtatPrestation etatPrestation,
			TypePrestation typePrestation, float prixPrestation, Client client) {
		super();
		this.adresseDePrestation = adresseDePrestation;
		this.dateDePrestation = dateDePrestation;
		this.etatPrestation = etatPrestation;
		this.typePrestation = typePrestation;
		this.prixPrestation = prixPrestation;
		this.client = client;
	}


	public void setClient(Client client) {
		this.client = client;
	}

	public Prestation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prestation( String adresseDePrestation, Date dateDePrestation, EtatPrestation etatPrestation,
			TypePrestation typePrestation, float prixPrestation) {
		super();
		this.adresseDePrestation = adresseDePrestation;
		this.dateDePrestation = dateDePrestation;
		this.etatPrestation = etatPrestation;
		this.typePrestation = typePrestation;
		this.prixPrestation = prixPrestation;
	}

	public Date getDateDePrestation() {
		return dateDePrestation;
	}

	public void setDateDePrestation(Date dateDePrestation) {
		this.dateDePrestation = dateDePrestation;
	}

	public Long getIdPrestation() {
		return idPrestation;
	}

	public void setIdPrestation(Long idPrestation) {
		this.idPrestation = idPrestation;
	}

	public String getAdresseDePrestation() {
		return adresseDePrestation;
	}

	public void setAdresseDePrestation(String adresseDePrestation) {
		this.adresseDePrestation = adresseDePrestation;
	}

	public Date getDateDePrivate() {
		return dateDePrestation;
	}

	public void setDateDePrivate(Date dateDePrestation) {
		this.dateDePrestation = dateDePrestation;
	}

	public EtatPrestation getEtatPrestation() {
		return etatPrestation;
	}

	public void setEtatPrestation(EtatPrestation etatPrestation) {
		this.etatPrestation = etatPrestation;
	}

	public TypePrestation getTypePrestation() {
		return typePrestation;
	}

	public void setTypePrestation(TypePrestation typePrestation) {
		this.typePrestation = typePrestation;
	}

	public float getPrixPrestation() {
		return prixPrestation;
	}

	public void setPrixPrestation(float prixPrestation) {
		this.prixPrestation = prixPrestation;
	}
	
}
