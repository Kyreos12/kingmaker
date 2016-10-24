package fr.rjacot.kingmaker.kingmaker.util.exception;

/**
 * Classe abstraite d'exception traitant toutes les {@link RuntimeException}s de tonapps.
 * 
 * @author G�osph�re
 */
public abstract class AbstractTonAppsRuntimeException extends RuntimeException {

	/**
	 * Num�ro de version pour le processus de s�rialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Appel de l'exception avec un message donn� en param�tre.
	 * 
	 * @param message
	 *            message � afficher
	 */
	public AbstractTonAppsRuntimeException(final String message) {
		super(message);
	}

	/**
	 * Appel de l'exception avec un throwable donn� en param�tre.
	 * 
	 * @param throwable
	 *            throwable � l'origine de l'exception
	 * @param message
	 *            message � afficher
	 */
	public AbstractTonAppsRuntimeException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
