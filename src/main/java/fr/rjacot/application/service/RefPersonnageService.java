package fr.rjacot.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rjacot.application.dao.RefPersonnageDao;
import fr.rjacot.application.domain.RefPersonnage;

@Service
public class RefPersonnageService {

	@Autowired
	private RefPersonnageDao dao;

	@Transactional(readOnly = true)
	public List<RefPersonnage> getPersonnages() {
		return dao.getPersonnages();
	}

	@Transactional
	public void creer(String nom) {

		final RefPersonnage lPerso = new RefPersonnage();
		lPerso.setNom(nom);
		dao.creer(lPerso);

	}
}
