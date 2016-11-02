package fr.rjacot.application.util.exception.runtime;

/**
 * Exception soulevée par les DAOs.
 * 
 * @author rjacot
 */
public class DAOException extends RuntimeException {

	/**
	 * Numéro de version pour le processus de sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Appel de l'exception avec le message par défaut.
	 */
	public DAOException() {
		super("Erreur lors de l'exécution d'une DAO.");
	}

	/**
	 * Appel de l'exception avec un message donnée en paramètre.
	 * 
	 * @param message
	 *            message à afficher
	 */
	public DAOException(final String message) {
		super(message);
	}

	/**
	 * Appel de l'exception avec un throwable donné en paramètre.
	 * 
	 * @param message
	 *            message à afficher
	 * @param throwable
	 *            throwable à l'origine de l'exception
	 */
	public DAOException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
