package fr.rjacot.kingmaker.connection.query;

import java.sql.Connection;
import java.sql.SQLException;

public class QueryUtils {

	public static boolean exists(Connection c, String query) throws SQLException {

		boolean ret = false;
		QueryResult qr = Query.executeSelect(c, query);
		
		while (qr.next()) {
			ret = true;
		}
		
		return ret;
	}

	public static Object getUniqueValue(Connection c, String query) throws SQLException {
		
		Object ret = null;
		QueryResult qr = Query.executeSelect(c, query);
		
		while (qr.next()) {
			ret = qr.getObject(1);
		}
		
		return ret;
	}
}
