package com.iknet.DemandeDePrestation.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.iknet.DemandeDePrestation.Entity.Abonnement;
import com.iknet.DemandeDePrestation.Entity.EtatPrestation;
import com.iknet.DemandeDePrestation.Entity.Prestation;
@Transactional
@RepositoryRestResource
public interface PrestationRepository extends JpaRepository<Prestation,Long>{
	Page<Prestation> findByClientIdClient(Long clientId, Pageable pageable);
    Optional<Prestation> findByIdPrestationAndClientIdClient(Long idPrestation, Long clientId);
    @Query("select prestation from Prestation prestation where prestation.etatPrestation=:etatPrestation")
    public Page<Prestation> findPrestationByEtat(@Param("etatPrestation") EtatPrestation etatPrestation, Pageable pageable);
    @Modifying
    @Query("update Prestation prestation set prestation.etatPrestation = :nouveauEtat where prestation.idPrestation=:idPrestation")
    public void modifierEtat(@Param("nouveauEtat")EtatPrestation etatPrestation,@Param("idPrestation") Long idPrestation);
    @Modifying
    @Query("delete Prestation prestation  where prestation.idPrestation=:idPrestation")
    public void supprimer(@Param("idPrestation") Long idPrestation);
    @Modifying
    @Query("update Prestation prestation set prestation.prixPrestation = :prixPrestation where prestation.idPrestation=:idPrestation")
    public void confirmer(@Param("prixPrestation")Float prixPrestation,@Param("idPrestation") Long idPrestation);

}
