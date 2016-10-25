package fr.rjacot.kingmaker.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;

import fr.rjacot.kingmaker.domain.Hexagone;
import fr.rjacot.kingmaker.service.HexagoneService;

@Controller
public class MapController {

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView accueil() throws SQLException {
		ModelAndView model = new ModelAndView("map");
		return model;
	}

	@RequestMapping(value = "/getHaxagones", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getHexagones(HttpServletRequest request) throws SQLException {

		ArrayList<Hexagone> elementSelect = (ArrayList<Hexagone>) HexagoneService.getHexagoneList();

		// Transformation des données au format attendu par Gson
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("values", elementSelect);
		map.put("nullable", 1);

		GsonBuilder gSonBuilder = new GsonBuilder();
		gSonBuilder.disableHtmlEscaping();
		return gSonBuilder.setPrettyPrinting().create().toJson(elementSelect);
	}

	@RequestMapping(value = "/updateHexagone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getHexagone(HttpServletRequest request) throws SQLException {

		Hexagone hexagone = new Hexagone();
		hexagone.setX(Integer.parseInt(request.getParameter("x")));
		hexagone.setY(Integer.parseInt(request.getParameter("y")));
		hexagone.setRoute(Boolean.parseBoolean(request.getParameter("route")));
		hexagone.setVisible(Boolean.parseBoolean(request.getParameter("visible")));
		hexagone.setRoyaume(Boolean.parseBoolean(request.getParameter("royaume")));
		hexagone.setNom(request.getParameter("nom"));

		HexagoneService.updateByCoordonnees(hexagone);

		return "Héxagone mis à jour";
	}
}
