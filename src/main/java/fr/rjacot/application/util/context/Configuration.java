package fr.rjacot.application.util.context;

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

import fr.rjacot.application.util.exception.runtime.FatalException;

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

	public static void checkSgbdVersion() {
		
		// TODO vérifier la version du SGBD
	}
}
