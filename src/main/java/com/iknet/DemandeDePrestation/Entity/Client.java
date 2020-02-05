package com.iknet.DemandeDePrestation.Entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeDeClient")
@DiscriminatorValue("Parent")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idClient;
	
	private String telephone;
	private String nom;
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL)
	private Collection<Prestation> prestations;
	
	public Client(Long idClient, String telephone, String nom, String mail) {
		super();
		this.idClient = idClient;
		this.telephone = telephone;
		this.nom = nom;
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Client [telephone=" + telephone + ", nom=" + nom + ", prestations=" + prestations + ", mail=" + mail
				+ "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Client(Long idClient, String telephone, String nom, String mail, Collection<Prestation> prestations) {
		super();
		this.idClient = idClient;
		this.telephone = telephone;
		this.nom = nom;
		this.mail = mail;
		this.prestations = prestations;
	}

	private String mail;
	
	
	
	public Client() {
		super();
		
	}

	public Client(String telephone, String mail) {
		super();
		this.telephone = telephone;
		this.mail = mail;
	}

	
	public Collection<Prestation> getPrestations() {
		return prestations;
	}
	
	public Client(String telephone, String mail, Collection<Prestation> prestations) {
		super();
		this.telephone = telephone;
		this.mail = mail;
		this.prestations = prestations;
	}

	public void setPrestations(Collection<Prestation> prestations) {
		this.prestations = prestations;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
