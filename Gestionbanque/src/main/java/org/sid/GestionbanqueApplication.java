package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.metier.ItfBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GestionbanqueApplication implements CommandLineRunner {
    
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private ItfBanqueMetier banqueMetier;
	public static void main(String[] args) {
		SpringApplication.run(GestionbanqueApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*  Cette partie sert d'insertion tant que dans application.properties on a "CREATE"
		 * Mais devient obselete dès lors qu'on utilise "UPDATE"
		Client c1=clientRepository.save(new Client("Toto","toeto@gmail.com"));
		Client c2=clientRepository.save(new Client("Tota","totia@gmail.com"));
		Client c3=clientRepository.save(new Client("Toti","totio@gmail.com"));
		Client c4=clientRepository.save(new Client("Tote","totea@gmail.com"));
		//-----------CompteCourant_CompteEpargne--------------------------------------//
		Compte cp1=compteRepository.save(new CompteCourant("c1",new Date(),19000,c1, -500));
		Compte cp2=compteRepository.save(new CompteCourant("c2",new Date(),29500,c2, -1500));
		Compte cp3=compteRepository.save(new CompteEpargne("c3",new Date(),312000,c3, 2.5));
		Compte cp4=compteRepository.save(new CompteEpargne("c4",new Date(),415000,c4, 3.5));
		//-------------Versement_Retrait------------------------------------------------//
		operationRepository.save(new Versement(new Date(),219000,cp1));
		operationRepository.save(new Versement(new Date(),129500,cp2));
		operationRepository.save(new Retrait(new Date(),712000,cp3));
		operationRepository.save(new Retrait(new Date(),815000,cp4));
		
		banqueMetier.verser("c1",2000001);
		*/
	}

}
