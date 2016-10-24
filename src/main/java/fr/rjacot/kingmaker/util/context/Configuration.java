package fr.rjacot.kingmaker.util.context;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import fr.rjacot.kingmaker.db.TestDatabase;
import fr.rjacot.kingmaker.util.exception.runtime.FatalException;

/**
 * Cette classe recueille les différents prérequis au niveau de l'environnement applicatif (système,
 * propriétés de l'application, bases de données).
 * 
 * @author Gfi
 */
public final class Configuration {

	/**
	 * Instance de Logger pour la journalisation de la classe
	 */
	private static final Logger LOG = Logger.getLogger(Configuration.class);

	/**
	 * Classe utilitaire. Le constructeur est privé pour ne pas l'instancier.
	 */
	private Configuration() {
	}

	public static void checkSystem() {

		LOG.info("Vérification de la version de JAVA");
		String version = System.getProperty("java.version");

		if (version == null) {
			throw new FatalException("Impossible de déterminer la version de Java");
		}

		version = version.toUpperCase(Locale.getDefault());
		LOG.info("JAVA version : " + version);

		// version de java
		if (version.compareTo("1.7.0") < 0) {
			throw new FatalException("Version de Java invalide (requis 1.7.0) : " + version);
		}

		try {

			ArrayList<String> drivers = new ArrayList<String>();
			drivers.add("org.postgresql.Driver");

			for (String driver : drivers) {

				LOG.info("Vérification de la présence du driver : " + driver);
				Class.forName(driver);
			}

		} catch (Exception e) {
			throw new FatalException("Driver introuvable", e);
		}

	}

	/**
	 * Vérifie que les bases de données sont accessibles.
	 */
	public static void checkDataBase() {
		
		Context initCtx = null;
		
		try {
			initCtx = new InitialContext();
			LOG.info("Récupération des resources JDBC dans le contexte");
			final NamingEnumeration<NameClassPair> names = initCtx.list("java:/comp/env/jdbc");

			// pour chaque ressource présente dans le contexte JDBC de l'annuaire
			while (names.hasMore()) {
				String jndiName= names.next().getName();
				LOG.info("Test de la connexion " + jndiName);
				Configuration.checkAvailableDatasourceJNDI(jndiName);
			}
		} catch (NamingException e) {
			throw new FatalException("Naming exception", e);
		} finally {
			try {
				// fermer le context
				if (initCtx != null) {
					initCtx.close();
				}
			} catch (Exception e) {
				throw new FatalException("Erreur lors de la fermeture du contexte", e);
			}
		}
	}
	
	/**
	 * Vérifie que les bases de données (identifiées par une ressource JNDI) sont accessibles.
	 * 
	 * @param jndiName
	 *            nom de la ressource JNDI
	 */
	private static void checkAvailableDatasourceJNDI(final String jndiName) {
		Context initCtx = null;
		Context envCtx = null;
		DataSource dataSource = null;

		try {
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:/comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/" + jndiName);
		} catch (NamingException e) {
			throw new FatalException("Naming exception", e);
		} finally {
			try {
				// fermer le context
				if (initCtx != null) {
					initCtx.close();
				}
			} catch (Exception e) {
				throw new FatalException("Erreur lors de la fermeture du contexte", e);
			}
		}

		if (dataSource == null) {
			throw new FatalException("Echec lors de la récupération de la datasource " + jndiName);
		} else {
			LOG.info("Datasource " + jndiName + " chargée");
			Connection connection = null;
			try {
				if ("kingmaker".equals(jndiName)) {
					KingmakerProperties.setDataSourceUrbanisme(dataSource);				
				}

				connection = dataSource.getConnection();
				final DatabaseMetaData dmd = connection.getMetaData();
				LOG.info("Base : " + dmd.getDatabaseProductName() + " "
						+ dmd.getDatabaseProductVersion());
			} catch (SQLException e) {
				throw new FatalException(
						"Impossible d'établir la connexion à la base de données correspondant à la ressource "
								+ jndiName, e);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new FatalException(
								"Impossible de fermer la connexion à la base de données correspondant à la ressource "
										+ jndiName, e);
					}
				}
			}
		}
	}

	public static void checkSgbdVersion() {
		
		try {
			
			if(!TestDatabase.isPopulated(KingmakerProperties.getConnexionUrbanisme())) {
			
			}
			
			System.out.println("1");
			//System.out.println());
			System.out.println("2");
			System.out.println(TestDatabase.getBaseVersion(KingmakerProperties.getConnexionUrbanisme()));
			System.out.println("3");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
