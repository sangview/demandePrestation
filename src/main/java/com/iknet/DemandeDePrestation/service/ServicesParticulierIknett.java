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

import com.iknet.DemandeDePrestation.Entity.Particulier;
import com.iknet.DemandeDePrestation.dao.ParticulierRepository;

@RestController
@CrossOrigin("*")
public class ServicesParticulierIknett {
	
	@Autowired
    private ParticulierRepository particulierRepository;

    @GetMapping("/particuliers")
    public Page<Particulier> getAllParticuliers(Pageable pageable) {
        return particulierRepository.findAll(pageable);
    }

    @PostMapping("/particuliers")
    public Particulier createParticulier(@Valid @RequestBody Particulier particulier) {
        return particulierRepository.save(particulier);
    }

    @PutMapping("/particuliers/{particulierId}")
    public Particulier updateParticulier(@PathVariable Long particulierId, @Valid @RequestBody Particulier particulierRequest) {
        return particulierRepository.findById(particulierId).map(particulier -> {
            particulier.setMail(particulierRequest.getMail());
            particulier.setNom(particulierRequest.getNom());   
            particulier.setTelephone(particulierRequest.getTelephone()); 
            return particulierRepository.save(particulier);
        }).orElseThrow(() -> new ResourceNotFoundException("ParticulierId " + particulierId + " not found"));
    }


    @DeleteMapping("/particuliers/{particulierId}")
    public ResponseEntity<?> deleteParticulier(@PathVariable Long particulierId) {
        return particulierRepository.findById(particulierId).map(particulier -> {
            particulierRepository.delete(particulier);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ParticulierId " + particulierId + " not found"));
    }

}
