package com.iknet.DemandeDePrestation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iknet.DemandeDePrestation.Entity.Client;
import com.iknet.DemandeDePrestation.Entity.EtatPrestation;
import com.iknet.DemandeDePrestation.Entity.Prestation;
import com.iknet.DemandeDePrestation.Entity.TypePrestation;
import com.iknet.DemandeDePrestation.dao.ClientRepository;
import com.iknet.DemandeDePrestation.dao.PrestationRepository;

@SpringBootApplication
public class DemandeDePrestationApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemandeDePrestationApplication.class, args);
	}

	
	public void run(String... args) throws Exception {			// TODO Auto-generated method stub
		
	}

}
