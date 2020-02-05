package com.iknet.DemandeDePrestation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.iknet.DemandeDePrestation.Entity.Client;
import com.iknet.DemandeDePrestation.Entity.Particulier;

@RepositoryRestResource
public interface ParticulierRepository  extends JpaRepository<Particulier, Long> {

}
