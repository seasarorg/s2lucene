package org.seasar.lucene.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.XAConnection;
import javax.sql.XADataSource;

import org.seasar.extension.dbcp.impl.XAConnectionImpl;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.DriverManagerUtil;
import org.seasar.framework.util.StringUtil;

public class XALuceneDataSourceImpl implements XADataSource {

	private Logger logger = Logger.getLogger(XALuceneDataSourceImpl.class);

	private String driverClassName;

	private String url;

	private String user;

	private String password;

	private Properties properties = new Properties();

	private int loginTimeout;

	private boolean isAutoCommit;

	public XALuceneDataSourceImpl() {
	}

	public XAConnection getXAConnection(String user, String password) throws SQLException {
		Connection con = null;
		if (StringUtil.isEmpty(user)) {
			con = DriverManager.getConnection(url);
		} else if (properties.isEmpty()) {
			con = DriverManager.getConnection(url, user, password);
		} else {
			Properties info = new Properties();
			info.putAll(properties);
			info.put("user", user);
			info.put("password", password);
			info.put("autoCommit", isAutoCommit);
			int currentLoginTimeout = DriverManager.getLoginTimeout();
			try {
				DriverManager.setLoginTimeout(loginTimeout);
				con = DriverManager.getConnection(url, info);
			} finally {
				try {
					DriverManager.setLoginTimeout(currentLoginTimeout);
				} catch (Exception e) {
					logger.log("ESSR0017", new Object[] { e.toString() }, e);
				}
			}
		}
		return new XAConnectionImpl(con);
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
		if (driverClassName != null && driverClassName.length() > 0) {
			DriverManagerUtil.registerDriver(driverClassName);
		}
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addProperty(String name, String value) {
		properties.put(name, value);
	}

	public XAConnection getXAConnection() throws SQLException {
		return getXAConnection(user, password);
	}

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	public void setLogWriter(final PrintWriter logWriter) throws SQLException {
	}

	public int getLoginTimeout() throws SQLException {
		return loginTimeout;
	}

	public void setLoginTimeout(final int loginTimeout) throws SQLException {
		this.loginTimeout = loginTimeout;
	}

	public boolean isAutoCommit() {
		return isAutoCommit;
	}

	public void setAutoCommit(boolean isAutoCommit) {
		this.isAutoCommit = isAutoCommit;
	}

}
