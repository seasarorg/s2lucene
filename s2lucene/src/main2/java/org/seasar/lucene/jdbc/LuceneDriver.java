package org.seasar.lucene.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

public class LuceneDriver implements java.sql.Driver {

	public static final int LUCENE_DRIVER_MAJOR_VERSION = 1;
	public static final int LUCENE_DRIVER_MINOR_VERSION = 0;

	private static final LuceneDriver INSTANCE = new LuceneDriver();

	static {
		try {
			DriverManager.registerDriver(INSTANCE);
		} catch (SQLException e) {
			throw new RuntimeException("LuceneDriver static method error");
		}
	}

	public boolean acceptsURL(String url) throws SQLException {
		if (url.startsWith("lucene")) {
			return true;
		}
		return false;
	}

	public Connection connect(String url, Properties info) throws SQLException {
		try {
			if (info == null) {
				info = new Properties();
			}
			if (!acceptsURL(url)) {
				return null;
			}
			synchronized (this) {
				return new LuceneConnection(url, info);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
		return INSTANCE;
	}

}
