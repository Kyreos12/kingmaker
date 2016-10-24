package fr.rjacot.kingmaker.connection.query;

/**
 * @author MBI
 */
@SuppressWarnings("rawtypes")
public class QueryMetaData {

	/**
	 * nom de la colonne.
	 */
	private final String name;

	/**
	 * type sql de la colonne.
	 */
	private final String sqlType;

	/**
	 * Type java de la colonne.
	 */
	private final Class javaType;

	/**
	 * pr�cision de la colonne (si num�rique).
	 */
	private final int precision;

	/**
	 * nombre de chiffre apr�s la virgule (si num�rique)
	 */
	private final int scale;

	/**
	 * flag d'acceptation des null.
	 */
	private final int nullable;

	/**
	 * constructeur.
	 * 
	 * @param name
	 *            String
	 * @param sqlType
	 *            String
	 * @param javaType
	 *            String
	 * @param precision
	 *            int
	 * @param scale
	 *            int
	 * @param nullable
	 *            boolean
	 * @throws ClassNotFoundException
	 *             ClassNotFoundException
	 */
	public QueryMetaData(final String name, final String sqlType, final String javaType,
			final int precision, final int scale, final int nullable) throws ClassNotFoundException {

		this.name = name;
		this.sqlType = sqlType;
		this.javaType = Class.forName(javaType);
		this.precision = precision;
		this.scale = scale;
		this.nullable = nullable;

	}

	/**
	 * Retourne le nom de la colonne.
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Retourne le type sql de la colonne.
	 * 
	 * @return String
	 */
	public String getSqlType() {
		return this.sqlType;
	}

	/**
	 * Retourne le type java de la colonne.
	 * 
	 * @return String
	 */
	public Class getJavaType() {
		return this.javaType;
	}

	/**
	 * Retourne la pr�cision de la colonne (si c'est un num�rique).
	 * 
	 * @return int
	 */
	public int getPrecision() {
		return this.precision;
	}

	/**
	 * Retourne le nombre de chiffre apr�s la virgule (si c'est un num�rique).
	 * 
	 * @return int
	 */
	public int getScale() {
		return this.scale;
	}

	/**
	 * Retourne true si la colonne accepte les null, false sinon.
	 * 
	 * @return boolean
	 */
	public int isNullable() {
		return this.nullable;
	}
}
