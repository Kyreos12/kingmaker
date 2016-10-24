package fr.rjacot.kingmaker.util.exception.runtime;

import fr.rjacot.kingmaker.kingmaker.util.exception.AbstractTonAppsRuntimeException;

/**
 * Exception soulev�e pour les cas o� l'application doit s'arr�ter.
 * 
 * @author G�osph�re
 */
public class FatalException extends AbstractTonAppsRuntimeException {

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
	public FatalException(final String message) {
		super(message);
	}

	/**
	 * Appel de l'exception avec un throwable donn� en param�tre.
	 * 
	 * @param message
	 *            message � afficher
	 * @param throwable
	 *            throwable � l'origine de l'exception
	 */
	public FatalException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
