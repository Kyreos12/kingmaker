package fr.rjacot.application.util.context;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ApplicationProperties {

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
	
}

