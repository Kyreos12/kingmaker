package fr.rjacot.kingmaker.util.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
// import org.springframework.orm.jpa.vendor.Database;

import fr.rjacot.kingmaker.util.exception.runtime.FatalException;

public class KingmakerListener implements ServletContextListener {

	/**
	 * Instance de Logger pour la journalisation de la classe
	 */
	private static final Logger LOG = Logger.getLogger(KingmakerListener.class);

	/**
	 * {@link ServletContext}.
	 */
	private static ServletContext servletContext;

	/**
	 * Renvoie un objet {@link ServletContext}.
	 *
	 * @return {@link ServletContext}
	 */
	public static ServletContext getContext() {
		return servletContext;
	}

	/**
	 * Modifie le {@link ServletContext}.
	 *
	 * @param servletContextEvent
	 *            {@link ServletContextEvent}
	 */
	public static void setContext(final ServletContextEvent servletContextEvent) {
		servletContext = servletContextEvent.getServletContext();
	}

	/**
	 * {@inheritDoc}
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		String applicationName = servletContextEvent.getServletContext()
				.getInitParameter("application.name");
		String applicationVersion = servletContextEvent.getServletContext()
				.getInitParameter("application.version");
		
		try {

			// Mettre ici tous les tests à établir au chargement de l'application
			LOG.info("Starting " + applicationName);
			LOG.info("");
			LOG.info(applicationName + " version " + applicationVersion);
			LOG.info("");
			Configuration.checkSystem();
			Configuration.checkDataBase();
			Configuration.checkSgbdVersion();
			
			
		} catch (FatalException e) {
			
			String erreur = "Démarage de " + applicationName + " impossible, un prérequis est non respecté";
			LOG.fatal(erreur, e);
			throw new FatalException(erreur, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
