package fr.rjacot.application.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import fr.rjacot.application.domain.Hexagone;

@Repository
public class HexagoneDao {

	@PersistenceContext
    private EntityManager entityManager;

    public List<Hexagone> getHexagones() {
        
    	final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaQuery<Hexagone> lCriteriaQuery = lCriteriaBuilder.createQuery(Hexagone.class);
        final Root<Hexagone> lRoot = lCriteriaQuery.from(Hexagone.class);
        lCriteriaQuery.select(lRoot);
        final TypedQuery<Hexagone> lTypedQuery = entityManager.createQuery(lCriteriaQuery);

        return lTypedQuery.getResultList();
    }
    
    public void addHexagone(final Hexagone hexagone) {
    	 entityManager.persist(hexagone);
    }

	public void update(Hexagone hexagone) {
		
		// TODO fonctionnement
		final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaUpdate<Hexagone> lCriteriaUpdate = lCriteriaBuilder.createCriteriaUpdate(Hexagone.class);
        final Root<Hexagone> lRoot = lCriteriaUpdate.from(Hexagone.class);
        final Path<Hexagone> lPath = lRoot.get("id_hexagone");
        final Expression<Boolean> lExpression = lCriteriaBuilder.equal(lPath, hexagone.getIdHexagone());
        lCriteriaUpdate.where(lExpression);
        lCriteriaUpdate.set("nom", hexagone.getNom());
        lCriteriaUpdate.set("route", hexagone.getRoute());
        lCriteriaUpdate.set("royaume", hexagone.getRoyaume());
        lCriteriaUpdate.set("visiblee", hexagone.getVisible());
        final Query lQuery = entityManager.createQuery(lCriteriaUpdate);
        final int lRowCount = lQuery.executeUpdate();

        if (lRowCount != 1) {
            final org.hibernate.Query lHQuery = lQuery.unwrap(org.hibernate.Query.class);
            final String lSql = lHQuery.getQueryString();
            throw new RuntimeException("Nombre d'occurences (" + lRowCount + ") modifiés différent de 1 pour " + lSql);
        }
		
	}
}
