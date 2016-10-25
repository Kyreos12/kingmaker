package fr.rjacot.kingmaker.db;

import java.sql.Connection;
import java.sql.SQLException;

import fr.rjacot.kingmaker.connection.query.QueryUtils;
import fr.rjacot.kingmaker.util.exception.runtime.FatalException;

/**
 * Classe permettant de tester si la base de données existe ou non.
 * 
 * @author Romain JACOT
 */
public final class TestDatabase {

	/**
	 * Constructeur
	 */
	private TestDatabase() {
	}

	/**
	 * Retourne vrai si la base de données possède la table version
	 * 
	 * @param c
	 * @return
	 * @throws SQLException
	 */
	public static boolean isPopulated(final Connection c) throws SQLException {

		final String query = "select * from information_schema.columns  where table_name = 'version'";
		return QueryUtils.exists(c, query);
	}

	/**
	 * Retourne le numéro de version de la base de données. Exemple : 2.0.
	 * 
	 * @param template
	 *            Accès à la base de données
	 * @return numéro de version de la base de données.
	 * @throws SQLException 
	 */
	public static String getBaseVersion(final Connection c) throws SQLException {
		
		final String query = "SELECT max(numero) as numero FROM version";
		return (String) QueryUtils.getUniqueValue(c, query);
	}
}
