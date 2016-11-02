package fr.rjacot.application.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.GsonBuilder;

import fr.rjacot.application.domain.Hexagone;
import fr.rjacot.application.html.CreateModalBuilder;
import fr.rjacot.application.service.HexagoneService;

@Controller
public class MapController {

	@Autowired
	private HexagoneService service;

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String accueil(ModelMap pModel) throws SQLException {
		
		/*String modal = new CreateModalBuilder<Hexagone>(Hexagone.class).renderer();
		pModel.addAttribute("modal", modal);*/
		return "map";
	}

	@RequestMapping(value = "/getHaxagones", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getHexagones(HttpServletRequest request) throws SQLException {

		GsonBuilder gSonBuilder = new GsonBuilder();
		gSonBuilder.disableHtmlEscaping();
		return gSonBuilder.setPrettyPrinting().create().toJson(service.getPersonnages());
	}

	@RequestMapping(value = "/updateHexagone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getHexagone(HttpServletRequest request) throws SQLException {

		service.update(request.getParameter("id_hexagone"), request.getParameter("nom"), request.getParameter("x"),
				request.getParameter("y"), request.getParameter("route"), request.getParameter("visible"),
				request.getParameter("royaume"));


		return "Héxagone mis à jour";
	}
}
