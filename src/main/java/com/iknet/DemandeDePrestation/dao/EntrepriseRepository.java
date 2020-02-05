package com.iknet.DemandeDePrestation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.iknet.DemandeDePrestation.Entity.Entreprise;

@RepositoryRestResource
public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {

	
}
