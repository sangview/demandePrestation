package com.iknet.DemandeDePrestation.Entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Particulier")
public class Particulier extends Client implements Serializable {

	public Particulier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Particulier(Long idClient, String telephone, String nom, String mail, Collection<Prestation> prestations) {
		super(idClient, telephone, nom, mail, prestations);
		// TODO Auto-generated constructor stub
	}

	public Particulier(Long idClient, String telephone, String nom, String mail) {
		super(idClient, telephone, nom, mail);
		// TODO Auto-generated constructor stub
	}

	public Particulier(String telephone, String mail, Collection<Prestation> prestations) {
		super(telephone, mail, prestations);
		// TODO Auto-generated constructor stub
	}

	public Particulier(String telephone, String mail) {
		super(telephone, mail);
		// TODO Auto-generated constructor stub
	}
	
	
}