package com.iknet.DemandeDePrestation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.iknet.DemandeDePrestation.Entity.Client;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

}
