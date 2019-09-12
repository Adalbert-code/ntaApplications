package org.sid.metier;

import java.util.Date;
import java.util.Optional;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//-------------------------------------------------------------------//
@Service
@Transactional
public class BanqueMetierImpl implements ItfBanqueMetier {
@Autowired
private CompteRepository compteRepository;
@Autowired
private OperationRepository operationRepository;

//@Override 
//public Compte consulterCompte(String codeCpte) {
//    Optional<Compte> cp = compteRepository.findOne(codeCpte);
//    if (cp==null) throw new RuntimeException("Compte introuvable");
//    return cp;
//}
//@Override
//public Compte consulterCompte(String codeCpte) {
//    Optional<Compte> optCompte = this.compteRepository.findById(codeCpte); // returns java8 optional
//    if (optCompte.isPresent()) {
//        return optCompte.get();
//    } else {
//        // handle not found, return null or throw
//    	// throw new RuntimeException("Compte introuvable");
//    	return null;
//    }
//}

//------------------------------------------------------//
@Override
public Compte consulterCompte(String codeCpte) {
  Optional<Compte> cp=compteRepository.findById(codeCpte);
  if (cp==null) throw new RuntimeException("Compte introuvable");
  return null;
}
//-------------------------------------------------------//}

@Override
public void verser(String codeCpte, double montant) {
    Compte cp = consulterCompte(codeCpte);
    Versement v = new Versement(new Date(), montant, cp);
    operationRepository.save(v);
    cp.setSolde((cp.getSolde() + montant));
    compteRepository.save(cp);
}

@Override
public void retirer(String codeCpte, double montant) {
    Compte cp = consulterCompte(codeCpte);
    double facilitesCaisse = 0;
    if (cp instanceof CompteCourant)
        facilitesCaisse = ((CompteCourant) cp).getDecouvert();
    if (cp.getSolde() + facilitesCaisse < montant)
        throw new RuntimeException("solde insuffisant");
    Retrait retrait = new Retrait(new Date(), montant, cp);
    operationRepository.save(retrait);
    cp.setSolde((cp.getSolde() - montant));
    compteRepository.save(cp);
}

@Override
public void virement(String codeCpte1, String codeCpte2, double montant) {
    if (codeCpte1.equals(codeCpte2))
    throw new RuntimeException("Virement non accordé");
	retirer(codeCpte1, montant);
    verser(codeCpte2, montant);
}

@Override
public Page<Operation> listOperation(String codeCpte, int page, int size) {
    return operationRepository.listOperation(codeCpte, PageRequest.of(page, size));
}
}
