package fr.rjacot.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.rjacot.application.domain.Hexagone;
import fr.rjacot.application.service.HexagoneService;

@Controller
public class HexagoneController {
	
	@Autowired
	private HexagoneService service;

	@RequestMapping(value = "/hexagone", method = RequestMethod.GET)
	public String afficher(ModelMap pModel) {

		final List<Hexagone> hexagones = service.getPersonnages();
		pModel.addAttribute("hexagones", hexagones);
		
		return "hexagone";
	}

	@RequestMapping(value = "/creerCreationListeCourses", method = RequestMethod.POST)
	public String create(final BindingResult pBindingResult, final ModelMap pModel) {

		final List<Hexagone> hexagones = service.getPersonnages();
		pModel.addAttribute("hexagones", hexagones);
		return "hexagone";
	}

}
