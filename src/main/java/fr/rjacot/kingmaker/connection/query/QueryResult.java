package fr.rjacot.kingmaker.connection.query;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author MBI
 */
public interface QueryResult {

	/**
	 * Fais avancer le curseur � la prochaine occurence.
	 *
	 * @return false si il n'y a plus d'occurences
	 * @throws SQLException
	 *             Exception SQL
	 */
	boolean next() throws SQLException;

	/**
	 * Clos le statement.
	 *
	 * @throws SQLException
	 *             Exception SQL
	 */
	void close() throws SQLException;

	/**
	 * R�cup�re les valeurs du Select.
	 *
	 * @return Ent�te
	 * @throws SQLException
	 *             Exception SQL
	 * @throws ClassNotFoundException
	 *             ClassNotFoundException
	 */
	List<QueryMetaData> getMetaDatas() throws SQLException, ClassNotFoundException;

	/**
	 * @return Map<String, QueryMetaData>
	 * @throws SQLException
	 *             SQLException
	 * @throws ClassNotFoundException
	 *             ClassNotFoundException
	 */
	Map<String, QueryMetaData> getMetaDatasMap() throws SQLException, ClassNotFoundException;

	/**
	 * @return La taille (nombre de lignes) renvoy�es par la requ�te
	 */
	int size();

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Cha�ne de caract�res
	 * @throws SQLException
	 *             Exception SQL
	 */
	String getString(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en BigDecimal
	 * @throws SQLException
	 *             Exception SQL
	 */
	BigDecimal getBigDecimal(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Blob
	 * @throws SQLException
	 *             Exception SQL
	 */
	Blob getBlob(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Boolean
	 * @throws SQLException
	 *             Exception SQL
	 */
	Boolean getBoolean(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Bytes
	 * @throws SQLException
	 *             Exception SQL
	 */
	byte[] getBytes(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Character
	 * @throws SQLException
	 *             Exception SQL
	 */
	Reader getCharacterStream(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Date
	 * @throws SQLException
	 *             Exception SQL
	 */
	Date getDate(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Double
	 * @throws SQLException
	 *             Exception SQL
	 */
	Double getDouble(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Float
	 * @throws SQLException
	 *             Exception SQL
	 */
	Float getFloat(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Int
	 * @throws SQLException
	 *             Exception SQL
	 */
	int getInt(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Long
	 * @throws SQLException
	 *             Exception SQL
	 */
	Long getLong(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Object
	 * @throws SQLException
	 *             Exception SQL
	 */
	Object getObject(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Array
	 * @throws SQLException
	 *             Exception SQL
	 */
	Array getArray(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Ascii
	 * @throws SQLException
	 *             Exception SQL
	 */
	InputStream getAsciiStream(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Integer
	 * @throws SQLException
	 *             Exception SQL
	 */
	Integer getInteger(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Integer
	 * @throws SQLException
	 *             Exception SQL
	 */
	Clob getClob(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Integer
	 * @throws SQLException
	 *             Exception SQL
	 */
	InputStream getBinaryStream(Object index) throws SQLException;

	/**
	 * @param index
	 *            Nom de la colonne
	 * @return La valeur de la colonne en Integer
	 * @throws SQLException
	 *             Exception SQL
	 * @throws IOException
	 *             IOException
	 */
	String getClobString(Object index) throws SQLException, IOException;

}
