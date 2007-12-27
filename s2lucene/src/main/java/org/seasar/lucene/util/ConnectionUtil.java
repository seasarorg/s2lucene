package org.seasar.lucene.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
