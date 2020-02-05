package com.iknet.DemandeDePrestation.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.iknet.DemandeDePrestation.Entity.Abonnement;
import com.iknet.DemandeDePrestation.Entity.Prestation;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement,Long>{

	Page<Abonnement> findByClientIdClient(Long clientId, Pageable pageable);
    Optional<Abonnement> findByIdPrestationAndClientIdClient(Long iAbonnement, Long clientId);
    
}
