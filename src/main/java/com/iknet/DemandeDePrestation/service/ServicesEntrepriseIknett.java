package com.iknet.DemandeDePrestation.service;

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

import com.iknet.DemandeDePrestation.Entity.Entreprise;
import com.iknet.DemandeDePrestation.dao.EntrepriseRepository;

@RestController
@CrossOrigin("*")
public class ServicesEntrepriseIknett {

	@Autowired
    private EntrepriseRepository entrepriseRepository;

    @GetMapping("/entreprises")
    public Page<Entreprise> getAllEntreprises(Pageable pageable) {
        return entrepriseRepository.findAll(pageable);
    }

    @PostMapping("/entreprises")
    public Entreprise createEntreprise(@Valid @RequestBody Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @PutMapping("/entreprises/{entrepriseId}")
    public Entreprise updateEntreprise(@PathVariable Long entrepriseId, @Valid @RequestBody Entreprise entrepriseRequest) {
        return entrepriseRepository.findById(entrepriseId).map(entreprise -> {
            entreprise.setMail(entrepriseRequest.getMail());
            entreprise.setNom(entrepriseRequest.getNom());   
            entreprise.setTelephone(entrepriseRequest.getTelephone()); 
            return entrepriseRepository.save(entreprise);
        }).orElseThrow(() -> new ResourceNotFoundException("EntrepriseId " + entrepriseId + " not found"));
    }


    @DeleteMapping("/entreprises/{entrepriseId}")
    public ResponseEntity<?> deleteEntreprise(@PathVariable Long entrepriseId) {
        return entrepriseRepository.findById(entrepriseId).map(entreprise -> {
            entrepriseRepository.delete(entreprise);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("EntrepriseId " + entrepriseId + " not found"));
    }

}
