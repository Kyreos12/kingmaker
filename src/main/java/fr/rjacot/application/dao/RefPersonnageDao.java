package fr.rjacot.application.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import fr.rjacot.application.domain.RefPersonnage;

@Repository
public class RefPersonnageDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<RefPersonnage> getPersonnages() {
		final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();

		final CriteriaQuery<RefPersonnage> lCriteriaQuery = lCriteriaBuilder.createQuery(RefPersonnage.class);
		final Root<RefPersonnage> lRoot = lCriteriaQuery.from(RefPersonnage.class);
		lCriteriaQuery.select(lRoot);
		final TypedQuery<RefPersonnage> lTypedQuery = entityManager.createQuery(lCriteriaQuery);

		return lTypedQuery.getResultList();
	}

	public void creer(RefPersonnage lPerso) {
		entityManager.persist(lPerso);

	}
}
