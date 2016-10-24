package fr.rjacot.kingmaker.util.context;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.sql.DataSource;

public class KingmakerProperties {

	static {
		SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.FRANCE);
		SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
	}

	/**
	 * Instance permettant de formater des dates (+heures) de façon uniforme pour l'affichage dans
	 * tout le projet.
	 */
	public static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT;

	/**
	 * Instance permettant de formater des dates de façon uniforme pour l'affichage dans tout le
	 * projet.
	 */
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT;
	
	/**
	 * Format du rapport Birt
	 */
	public static String birtFormat;
	
	/**
	 * Connexion Jdbc à la base de Paris
	 */
	private static DataSource dataSourceUrbanisme;

	public static DataSource getPDataSourceUrbanisme() {
		return KingmakerProperties.dataSourceUrbanisme;
	}
	
	public static Connection getConnexionUrbanisme() throws SQLException {
		return KingmakerProperties.dataSourceUrbanisme.getConnection();
	}

	public static void setDataSourceUrbanisme(DataSource dataSource) {
		KingmakerProperties.dataSourceUrbanisme = dataSource;
	}
	
}

