package com.iknet.DemandeDePrestation.Entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Entreprise")
public class Entreprise extends Client implements Serializable {

	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(Long idClient, String telephone, String nom, String mail, Collection<Prestation> prestations) {
		super(idClient, telephone, nom, mail, prestations);
		// TODO Auto-generated constructor stub
	}

	public Entreprise(Long idClient, String telephone, String nom, String mail) {
		super(idClient, telephone, nom, mail);
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String telephone, String mail, Collection<Prestation> prestations) {
		super(telephone, mail, prestations);
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String telephone, String mail) {
		super(telephone, mail);
		// TODO Auto-generated constructor stub
	}

	
	
}