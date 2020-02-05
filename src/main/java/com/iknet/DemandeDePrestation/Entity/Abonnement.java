package com.iknet.DemandeDePrestation.Entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Abonnement")
public class Abonnement  extends Prestation implements Serializable  {

	private PeriodiciteAbonnement periodicite;
}
