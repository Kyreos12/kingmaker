package fr.rjacot.kingmaker.connection.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QueryResultJdbc implements QueryResult {

	/**
	 * Resultset contenant les résultats.
	 */
	private final ResultSet resultSet;

	/**
	 * Nombre d'éléments dans le résultset.
	 */
	private final int size;
	
	QueryResultJdbc(final ResultSet rs, final int s) {

		this.resultSet = rs;
		this.size = s;
	}
	
	@Override
	public boolean next() throws SQLException {
		// Si le ResultSet n'est pas null.
		// Rajout Access
		if (this.resultSet != null) {
			// On avance le curseur
			if (this.resultSet.next()) {
				return true;
			} else if (this.resultSet != null) {
				// Si il n'y a plus de donn�es, on ferme le resultSet
				this.resultSet.getStatement().close();
			}
		}
		return false;
	}

	@Override
	public void close() throws SQLException {
		// Si le ResultSet est instanci�
		if (this.resultSet != null && !this.resultSet.isClosed()) {
			// R�cup�ration du statement
			final Statement s = this.resultSet.getStatement();

			// Si il n'est pas clos, on le clos.
			if (s != null) {
				s.close();
			}
		}
	}

	@Override
	public List<QueryMetaData> getMetaDatas() throws SQLException, ClassNotFoundException {

		final ResultSetMetaData resulSetMetaData = this.resultSet.getMetaData();
		final List<QueryMetaData> metaDatas = new LinkedList<QueryMetaData>();

		for (int i = 1; i <= resulSetMetaData.getColumnCount(); i++) {

			try {
				metaDatas.add(new QueryMetaData(resulSetMetaData.getColumnName(i),
						resulSetMetaData.getColumnTypeName(i),
						resulSetMetaData.getColumnClassName(i),
						resulSetMetaData.getPrecision(i), resulSetMetaData.getScale(i),
						resulSetMetaData.isNullable(i)));
			} catch (Exception e) {
				System.out.println(resulSetMetaData.getColumnName(i));
			}
		}

		return metaDatas;
	}

	public Map<String, QueryMetaData> getMetaDatasMap()
			throws SQLException, ClassNotFoundException {

		final ResultSetMetaData resulSetMetaData = this.resultSet.getMetaData();
		final Map<String, QueryMetaData> metaDatas = new HashMap<String, QueryMetaData>();

		for (int i = 1; i <= resulSetMetaData.getColumnCount(); i++) {

			metaDatas.put(resulSetMetaData.getColumnName(i).toUpperCase(),
					new QueryMetaData(resulSetMetaData.getColumnName(i),
							resulSetMetaData.getColumnTypeName(i),
							resulSetMetaData.getColumnClassName(i),
							resulSetMetaData.getPrecision(i), resulSetMetaData.getScale(i),
							resulSetMetaData.isNullable(i)));

			metaDatas.put(resulSetMetaData.getColumnName(i).toLowerCase(),
					new QueryMetaData(resulSetMetaData.getColumnName(i),
							resulSetMetaData.getColumnTypeName(i),
							resulSetMetaData.getColumnClassName(i),
							resulSetMetaData.getPrecision(i), resulSetMetaData.getScale(i),
							resulSetMetaData.isNullable(i)));

		}

		return metaDatas;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public String getString(final Object index) throws SQLException {

		if (index.getClass() == String.class) {

			String value = this.resultSet.getString((String) index);
			if (value != null) {
				value = value.trim();

				if (value.length() == 0) {
					value = null;
				}
			}
			return value;
		} else if (index.getClass() == Integer.class) {
			String value = this.resultSet.getString((Integer) index);
			if (value != null) {
				value = value.trim();
			}
			return value;
		} else {
			return null;
		}
	}

	@Override
	public BigDecimal getBigDecimal(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getBigDecimal((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getBigDecimal((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public Blob getBlob(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getBlob((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getBlob((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public Boolean getBoolean(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getBoolean((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getBoolean((Integer) index);
		} else {
			return false;
		}
	}

	@Override
	public byte[] getBytes(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getBytes((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getBytes((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public Reader getCharacterStream(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getCharacterStream((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getCharacterStream((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public Date getDate(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getDate((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getDate((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public Double getDouble(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getDouble((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getDouble((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public Float getFloat(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getFloat((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getFloat((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public int getInt(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getInt((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getInt((Integer) index);
		} else {
			return 0;
		}
	}

	@Override
	public Long getLong(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getLong((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getLong((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public Object getObject(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getObject((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getObject((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public Array getArray(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getArray((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getArray((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public InputStream getAsciiStream(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getAsciiStream((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getAsciiStream((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public Integer getInteger(final Object index) throws SQLException {
		BigDecimal bigDecimal = null;

		if (index.getClass() == String.class) {
			bigDecimal = this.resultSet.getBigDecimal((String) index);
		} else if (index.getClass() == Integer.class) {
			bigDecimal = this.resultSet.getBigDecimal((Integer) index);
		} else {
			return null;
		}

		if (bigDecimal != null) {
			return bigDecimal.intValueExact();
		}

		return null;
	}

	@Override
	public Clob getClob(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getClob((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getClob((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public InputStream getBinaryStream(final Object index) throws SQLException {

		if (index.getClass() == String.class) {
			return this.resultSet.getBinaryStream((String) index);
		} else if (index.getClass() == Integer.class) {
			return this.resultSet.getBinaryStream((Integer) index);
		} else {
			return null;
		}
	}

	@Override
	public String getClobString(final Object index) throws SQLException, IOException {

		final InputStream is = this.getBinaryStream(index);

		if (is != null) {

			final StringWriter sw = new StringWriter();
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader buffer = new BufferedReader(isr);
			String line = "";

			while (null != (line = buffer.readLine())) {
				sw.write(line);
			}

			return sw.toString();
		}

		return null;
	}
}
