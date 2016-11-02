package fr.rjacot.application.controller.references;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.rjacot.application.domain.RefPersonnage;
import fr.rjacot.application.html.CreateModalBuilder;
import fr.rjacot.application.service.RefPersonnageService;

@Controller
@RequestMapping(value = "/references")
public class RefPersonnageController {

	@Autowired
	private RefPersonnageService service;

	@RequestMapping(value = "/RefPersonnage", method = RequestMethod.GET)
	public String afficher(ModelMap pModel) {

		HashMap<String, String> replaceTexte = new HashMap<>();
		replaceTexte.put("nom", "Nom");
		String modal = new CreateModalBuilder<RefPersonnage>(RefPersonnage.class, CreateModalBuilder.ADD, replaceTexte).renderer();
		
		pModel.addAttribute("modal", modal);

		final List<RefPersonnage> listePersonnages = service.getPersonnages();
		pModel.addAttribute("personnages", listePersonnages);
		return "references/personnage";
	}

	@RequestMapping(value = "/RefPersonnage", method = RequestMethod.POST)
	public String creer(@RequestBody HttpServletRequest request, final ModelMap pModel) {

		String nom = request.getParameter("editNom");

		if (nom != null) {
			service.creer(nom);
		}

		return afficher(pModel);
	}
}