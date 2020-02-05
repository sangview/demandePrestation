package com.iknet.DemandeDePrestation.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iknet.DemandeDePrestation.Entity.Abonnement;
import com.iknet.DemandeDePrestation.Entity.PeriodiciteAbonnement;
import com.iknet.DemandeDePrestation.Entity.Prestation;
import com.iknet.DemandeDePrestation.dao.AbonnementRepository;
import com.iknet.DemandeDePrestation.dao.ClientRepository;
import com.iknet.DemandeDePrestation.dao.PrestationRepository;

@RestController
@CrossOrigin("*")
public class ServicesAbonnementIknet {

	@GetMapping("/typesAbonnement")
	public Collection<PeriodiciteAbonnement> typesAbonnement(){
		List<PeriodiciteAbonnement> roleList = Arrays.asList(PeriodiciteAbonnement.values());
		return roleList;
		
	}

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients/{clientId}/abonnements")
    public Page<Abonnement> getAllAbonnementsByClientId(@PathVariable (value = "clientId") Long clientId,
                                                Pageable pageable) {
        return abonnementRepository.findByClientIdClient(clientId, pageable);
    }

    @PostMapping("/clients/{clientId}/abonnements")
    public Abonnement createAbonnement(@PathVariable (value = "clientId") Long clientId,
                                 @Valid @RequestBody Abonnement abonnement) {
        return clientRepository.findById(clientId).map(client -> {
            abonnement.setClient(client);
            return abonnementRepository.save(abonnement);
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + clientId + " not found"));
    }

    @PutMapping("/clients/{clientId}/abonnements/{abonnementId}")
    public Abonnement updateAbonnement(@PathVariable (value = "clientId") Long clientId,
                                 @PathVariable (value = "abonnementId") Long abonnementId,
                                 @Valid @RequestBody Abonnement abonnementRequest) {
        if(!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("ClientId " + clientId + " not found");
        }

        return abonnementRepository.findById(abonnementId).map(abonnement -> {
            abonnement.setPrixPrestation(abonnementRequest.getPrixPrestation());
            return abonnementRepository.save(abonnement);
        }).orElseThrow(() -> new ResourceNotFoundException("AbonnementId " + abonnementId + "not found"));
    }

    @DeleteMapping("/clients/{clientId}/abonnements/{abonnementId}")
    public ResponseEntity<?> deleteAbonnement(@PathVariable (value = "clientId") Long clientId,
                              @PathVariable (value = "abonnementId") Long abonnementId) {
        return abonnementRepository.findByIdPrestationAndClientIdClient(abonnementId, clientId).map(abonnement -> {
            abonnementRepository.delete(abonnement);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Abonnement not found with id " + abonnementId + " and clientId " + clientId));
    }
	
}
