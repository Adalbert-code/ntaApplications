package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.ItfBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BanqueController {
	@Autowired
	private ItfBanqueMetier banqueMetier;
	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}
	@RequestMapping("/consulterCompte")
	public String consulter(Model model,String codeCompte) {
		model.addAttribute("codeCompte",codeCompte);
		try {
		Compte cp=banqueMetier.consulterCompte(codeCompte);
		Page<Operation> pageOperations=banqueMetier.listOperation(codeCompte, 0, 4);
		model.addAttribute("listoperations", pageOperations.getContent());
		model.addAttribute("compte", cp);
		}
		catch(Exception e) {
		model.addAttribute("exception", e);
		}
		return "comptes";
	}
	@RequestMapping(value="/saveOperation",method=RequestMethod.POST)
	public String saveOperation(Model model,String typeOperation,String codeCompte,double montant,String codeCompte2) {
		
		try {
			if(typeOperation.contentEquals("VERS")){
				banqueMetier.retirer(codeCompte, montant);
			}
			else if (typeOperation.contentEquals("RET")){
				banqueMetier.retirer(codeCompte, montant);
			}
			if(typeOperation.contentEquals("VIR")){
				banqueMetier.retirer(codeCompte, montant);
			}
		} catch (Exception e) {
			model.addAttribute("error", e); // TODO: handle exception
			return "redirect:/consulterCompte?cedeCompte="+codeCompte
					+"&error="+e.getMessage();
		}
		
		
		return "redirect:/consulterCompte?cedeCompte="+codeCompte;
	}
}
