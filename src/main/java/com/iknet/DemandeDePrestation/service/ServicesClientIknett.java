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

import com.iknet.DemandeDePrestation.Entity.Client;
import com.iknet.DemandeDePrestation.dao.ClientRepository;

@RestController
@CrossOrigin("*")
public class ServicesClientIknett {
 
	
	//
	@Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @PostMapping("/clients")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/clients/{clientId}")
    public Client updateClient(@PathVariable Long clientId, @Valid @RequestBody Client clientRequest) {
        return clientRepository.findById(clientId).map(client -> {
            client.setMail(clientRequest.getMail());
            client.setNom(clientRequest.getNom());   
            client.setTelephone(clientRequest.getTelephone()); 
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + clientId + " not found"));
    }


    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        return clientRepository.findById(clientId).map(client -> {
            clientRepository.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + clientId + " not found"));
    }

}
