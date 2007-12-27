package org.seasar.lucene.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Lucene�p�h���C�o�[
 * 
 * @author a-conv
 * 
 */
public class LuceneDriver implements java.sql.Driver {

	public static final int LUCENE_DRIVER_MAJOR_VERSION = 1;
	public static final int LUCENE_DRIVER_MINOR_VERSION = 0;

	private static final LuceneDriver INSTANCE = new LuceneDriver();

	static {
		System.out.println("LuceneDriver Create!!");
		try {
			DriverManager.registerDriver(INSTANCE);
		} catch (SQLException e) {
			throw new RuntimeException("LuceneDriver static method error");
		}
	}

	public boolean acceptsURL(String url) throws SQLException {
		if (url.startsWith("lucene")) {
			System.out.println("LuceneDriver acceptsURL OK!!");
			return true;
		}
		return false;
	}

	public Connection connect(String url, Properties info) throws SQLException {
		System.out.println("LuceneDriver Connect Call" + url);
		Connection connection = new LuceneConnection();
		return connection;
	}

	public int getMajorVersion() {
		return LUCENE_DRIVER_MAJOR_VERSION;
	}

	public int getMinorVersion() {
		return LUCENE_DRIVER_MINOR_VERSION;
	}

	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		return new DriverPropertyInfo[0];
	}

	public boolean jdbcCompliant() {
		return true;
	}

	public static LuceneDriver load() {
		System.out.println("LuceneDriver Load!!");
		return INSTANCE;
	}

}
