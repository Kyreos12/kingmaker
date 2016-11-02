package fr.rjacot.application.util.exception.runtime;

/**
 * Exception soulevée pour les cas ou l'application doit s'arrêter.
 * 
 * @author rjacot
 */
public class FatalException extends RuntimeException {

	/**
	 * Num�ro de version pour le processus de s�rialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Appel de l'exception avec un message donné en paramètre.
	 * 
	 * @param message
	 *            message à afficher
	 */
	public FatalException(final String message) {
		super(message);
	}

	/**
	 * Appel de l'exception avec un throwable donn� en param�tre.
	 * 
	 * @param message
	 *            message à afficher
	 * @param throwable
	 *            throwable à l'origine de l'exception
	 */
	public FatalException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
