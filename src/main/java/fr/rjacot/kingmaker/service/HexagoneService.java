package fr.rjacot.kingmaker.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import fr.rjacot.kingmaker.domain.Hexagone;
import fr.rjacot.kingmaker.util.context.KingmakerProperties;

public class HexagoneService {

	private static final Logger LOG = Logger.getLogger(HexagoneService.class);

	public static List<Hexagone> getHexagoneList() throws SQLException {

		String sql = "select * from hexagone";

		QueryRunner qRunner = new QueryRunner(KingmakerProperties.getPDataSourceUrbanisme());

		return (List<Hexagone>) qRunner.query(sql, new BeanListHandler<Hexagone>(Hexagone.class));
	}

	public static void updateByCoordonnees(Hexagone hexagone) throws SQLException {

		LOG.debug("Mise à jour de l'héxaogne " + hexagone.getX() + "-" + hexagone.getY());
		String sql = "update hexagone " + "set visible = ?," + "royaume = ?," + "route = ?," + "nom = ? "
				+ "where x = ? and y = ?";

		QueryRunner run = new QueryRunner(KingmakerProperties.getPDataSourceUrbanisme());
		run.update(sql, hexagone.getVisible(), hexagone.getRoyaume(), hexagone.getRoute(), hexagone.getNom(),
				hexagone.getX(), hexagone.getY());
	}
}
