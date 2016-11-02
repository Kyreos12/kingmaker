package fr.rjacot.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rjacot.application.dao.HexagoneDao;
import fr.rjacot.application.domain.Hexagone;

@Service
public class HexagoneService {

	@Autowired
    private HexagoneDao dao;

    @Transactional(readOnly=true)
    public List<Hexagone> getPersonnages() {
        return dao.getHexagones();
    }

    @Transactional
	public void add(Hexagone hexagone) {
		dao.addHexagone(hexagone);
	}

	@Transactional
	public void update(Hexagone hexagone) {
		dao.update(hexagone);		
	}

	public void update(String idHexagone, String nom, String x, String y, String route,
			String visible, String royaume) {

		Hexagone hexagone = new Hexagone();
		hexagone.setX(Integer.parseInt(x));
		hexagone.setY(Integer.parseInt(y));
		hexagone.setRoute(Boolean.parseBoolean(route));
		hexagone.setVisible(Boolean.parseBoolean(visible));
		hexagone.setRoyaume(Boolean.parseBoolean(royaume));
		hexagone.setNom(nom);
		this.update(hexagone);
	}
}
