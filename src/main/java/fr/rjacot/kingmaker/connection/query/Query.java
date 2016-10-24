package fr.rjacot.kingmaker.connection.query;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

/**
 * Classe permettant d'effectuer des requêtes SQL.
 *
 * @author Gfi
 */
public final class Query {

	private static int typeResultSet = ResultSet.TYPE_SCROLL_SENSITIVE;
	
	private static final Logger LOG = Logger.getLogger(Query.class);

	/**
	 * Execute une requête SELECT avec un tableau de paramètre
	 * 
	 * @param conn
	 *            connexion à la base de données
	 * @param sql
	 *            requête
	 * @param lParams
	 *            Liste d'objets
	 * @return Résultat de la requête
	 * @throws SQLException
	 */
	public static QueryResult executeSelect(Connection conn, final String sql,
			Object[] lParams) throws SQLException {

		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = getPreparedStatement(conn, sql, lParams, null);
			final ResultSet resultSet = preparedStatement.executeQuery();
			return new QueryResultJdbc(resultSet, -1);
		} catch (SQLException e) {
			throw e;
		} finally {
			LOG.debug(preparedStatement.toString());
		}
	}

	/**
	 * Execute une requête SELECT sans paramètres
	 * 
	 * @param conn
	 *            connexion à la base de données
	 * @param sql
	 *            requête
	 * @param lParams
	 *            Liste d'objets
	 * @return Résultat de la requête
	 * @throws SQLException
	 */
	public static QueryResult executeSelect(Connection conn, final String sql) throws SQLException {

		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(conn, sql, null, null);
		final ResultSet resultSet = preparedStatement.executeQuery();
		LOG.debug(preparedStatement.toString());

		return new QueryResultJdbc(resultSet, -1);
	}
	
	public static int count(final Connection conn, final String sql,
			Object[] lParams) throws SQLException {

		int count = 0;
		final QueryResult qResult = Query.executeSelect(conn, sql, lParams);

		while (qResult.next()) {
			count = qResult.getInt(1);
		}

		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private static PreparedStatement getPreparedStatement(final Connection conn, final String sql,
			final Object[] param, final String[] generatedKeys, final boolean updateNullValue)
			throws SQLException {

		PreparedStatement preparedStatement = generatedKeys != null
				? conn.prepareStatement(sql, generatedKeys)
				: conn.prepareStatement(sql, typeResultSet, ResultSet.CONCUR_READ_ONLY);

		Query.setParam(preparedStatement, param, updateNullValue);

		return preparedStatement;

	}

	private static PreparedStatement getPreparedStatement(final Connection conn, final String sql,
			final Object[] param, final String[] generatedKeys) throws SQLException {

		return Query.getPreparedStatement(conn, sql, param, generatedKeys, false);

	}

	private static void setParam(final PreparedStatement preparedStatement, final Object[] param,
			final boolean updateNullValue) throws SQLException {

		if (param != null) {

			int i = 1;

			for (final Object o : param) {

				if (o != null) {
					if (o.getClass() == Integer.class) {
						preparedStatement.setInt(i++, (Integer) o);
					} else if (o.getClass() == Float.class) {
						preparedStatement.setFloat(i++, (Float) o);
					} else if (o.getClass() == String.class) {
						preparedStatement.setString(i++, (String) o);
					} else if (o.getClass() == Date.class) {
						preparedStatement.setDate(i++, (Date) o);
					} else if (o.getClass() == Boolean.class) {
						preparedStatement.setBoolean(i++, (Boolean) o);
					} else if (o.getClass() == Double.class) {
						preparedStatement.setDouble(i++, (Double) o);
					} else if (o.getClass() == Timestamp.class) {
						preparedStatement.setTimestamp(i++, (Timestamp) o);
					} else if (o.getClass() == BigDecimal.class) {
						preparedStatement.setBigDecimal(i++, (BigDecimal) o);
					} else {
						System.err.println(o.getClass() + " n'est pas geré par le SetParam");
					}
				} else if (updateNullValue) {
					preparedStatement.setNull(i++, 0);
				}
			}
		}
	}

	
}
