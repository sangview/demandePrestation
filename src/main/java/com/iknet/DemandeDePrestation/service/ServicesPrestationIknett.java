package com.iknet.DemandeDePrestation.service;

import javax.transaction.Transactional;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.iknet.DemandeDePrestation.Entity.Client;
import com.iknet.DemandeDePrestation.Entity.EtatPrestation;
import com.iknet.DemandeDePrestation.Entity.Prestation;
import com.iknet.DemandeDePrestation.dao.ClientRepository;
import com.iknet.DemandeDePrestation.dao.PrestationRepository;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RestController
@CrossOrigin("*")
public class ServicesPrestationIknett {

	@Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private ClientRepository clientRepository;
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
       public void envoyerMail(String sujet, String corpsDuCourier, String adresseDestinateur)  throws Exception {
    	

     
    	ServicesPrestationIknett obj = new ServicesPrestationIknett();

            try {
               
            	obj.sendPost(sujet, corpsDuCourier, adresseDestinateur);
            } finally {
                obj.close();
            }
        
    }
       private void close() throws IOException {
           httpClient.close();
       }

       private void sendPost(String sujet, String corpsDuCourier, String adresseDestinateur) throws Exception {

           HttpPost post = new HttpPost("http://messagerie.cfapps.io//envoieMail");

           //Mot de Passe de rapide.ikneet@gmail.com: fohqfalucrisczii
           // add request parameter, form parameters
           List<NameValuePair> urlParameters = new ArrayList<>();
           urlParameters.add(new BasicNameValuePair("adresseExpediteur", "rapide.iknett@gmail.com"));
           urlParameters.add(new BasicNameValuePair("motDePasseExpediteur", "fohqfalucrisczii"));
           urlParameters.add(new BasicNameValuePair("sujet",sujet));
           urlParameters.add(new BasicNameValuePair("corpsDuCourier",corpsDuCourier));
           urlParameters.add(new BasicNameValuePair("adresseDestinateur",adresseDestinateur));

           post.setEntity(new UrlEncodedFormEntity(urlParameters));

           try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(post)) {

               System.out.println(EntityUtils.toString(response.getEntity()));
           }

       }

    
    @GetMapping("/clients/{clientId}/prestations")
    public Page<Prestation> getAllPrestationsByClientId(@PathVariable (value = "clientId") Long clientId,
                                                Pageable pageable) {
        return prestationRepository.findByClientIdClient(clientId, pageable);
    }
    
    @GetMapping("/prestations/{prestationId}/client")
    public String getClientByPrestationId(@PathVariable (value = "prestationId") Long prestationId) throws JsonProcessingException {
    	Client client= prestationRepository.getOne(prestationId).getClient();
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	String jsonClient = ow.writeValueAsString(client);
    	return jsonClient;
    }

    @GetMapping("/prestations/{etatPrestation}")
    public Page<Prestation>  getPrestationByEtat(@PathVariable (value = "etatPrestation") String etatPrestation,
            Pageable pageable) {
    	if(etatPrestation.compareTo("enAttente")==0) {
    		return prestationRepository.findPrestationByEtat(EtatPrestation.enAttente,pageable);
    	}
    	else if(etatPrestation.compareTo("enCours")==0){
    		return prestationRepository.findPrestationByEtat(EtatPrestation.enCours,pageable);
    	}
    	else if(etatPrestation.compareTo("confirmee")==0){
    		return prestationRepository.findPrestationByEtat(EtatPrestation.confirmee,pageable);
    	}
    	else {
    		return prestationRepository.findPrestationByEtat(EtatPrestation.achevee,pageable);
    	}
    }

    @GetMapping("modification-etat/prestation/{idPrestation}/{nouveauEtat}")
    public void modifierEtatPrestation(@PathVariable(value="idPrestation")Long idPrestation,@PathVariable(value="nouveauEtat") String nouveauEtat) {
    	if(nouveauEtat.compareTo("enAttente")==0) {
    		prestationRepository.modifierEtat(EtatPrestation.enAttente, idPrestation);
    	}
    	else if(nouveauEtat.compareTo("enCours")==0) {
    		prestationRepository.modifierEtat(EtatPrestation.enCours, idPrestation);
    	}
    	else if(nouveauEtat.compareTo("confirmee")==0) {
    		prestationRepository.modifierEtat(EtatPrestation.confirmee, idPrestation);
		}
    	else if(nouveauEtat.compareTo("achevee")==0) {
    		prestationRepository.modifierEtat(EtatPrestation.achevee, idPrestation);  		
    	}
    }
    @GetMapping("supprimerEtatPrestation/prestation/{idPrestation}")
    public void supprimerPrestation(@PathVariable(value="idPrestation")Long idPrestation) {
    	prestationRepository.supprimer(idPrestation);
    }
    
    @GetMapping("confirmer/prestation/{idPrestation}/{prixPrestation}")
    public void confirmerPrestation(@PathVariable(value="idPrestation")Long idPrestation,@PathVariable(value="prixPrestation")float prixPrestation) throws Exception {
    	prestationRepository.confirmer(prixPrestation, idPrestation);
    	prestationRepository.modifierEtat(EtatPrestation.confirmee, idPrestation);
    	/*Prestation prestation= prestationRepository.getOne(idPrestation);
    	Client client= prestation.getClient();
    	String sujet="Confirmation de prestation - IKNETT",
    			corpsDuCourier="Bonjour "+client.getNom()+"\n"+"\n"+"\n"+
    				  "Ci-joint. les informations concernant votre demande de prestation:"+"\n"+
    				  "Type de la prestation:"+prestation.getTypePrestation()+"\n"+
    				  "Prix de la prestation:"+prestation.getPrixPrestation()+"\n"+
    				  "Type d'abonnement:"+prestation.getTypePrestation()+"\n"+
    				  "Adresse de la prestation:"+prestation.getAdresseDePrestation()+"\n";
    	
    	
    	envoyerMail(sujet,  corpsDuCourier, client.getMail());
    	
    	*/
    }
    
    @PostMapping("/clients/{clientId}/prestations")
    public Prestation createPrestation(@PathVariable (value = "clientId") Long clientId,
                                 @Valid @RequestBody Prestation prestation) {
        return clientRepository.findById(clientId).map(client -> {
            prestation.setClient(client);           
            return prestationRepository.save(prestation);
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + clientId + " not found"));
    }

    @PutMapping("/clients/{clientId}/prestations/{prestationId}")
    public Prestation updatePrestation(@PathVariable (value = "clientId") Long clientId,
                                 @PathVariable (value = "prestationId") Long prestationId,
                                 @Valid @RequestBody Prestation prestationRequest) {
        if(!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("ClientId " + clientId + " not found");
        }

        return prestationRepository.findById(prestationId).map(prestation -> {
            prestation.setPrixPrestation(prestationRequest.getPrixPrestation());
            return prestationRepository.save(prestation);
        }).orElseThrow(() -> new ResourceNotFoundException("PrestationId " + prestationId + "not found"));
    }

    @DeleteMapping("/clients/{clientId}/prestations/{prestationId}")
    public ResponseEntity<?> deletePrestation(@PathVariable (value = "clientId") Long clientId,
                              @PathVariable (value = "prestationId") Long prestationId) {
        return prestationRepository.findByIdPrestationAndClientIdClient(prestationId, clientId).map(prestation -> {
            prestationRepository.delete(prestation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Prestation not found with id " + prestationId + " and clientId " + clientId));
    }

}
