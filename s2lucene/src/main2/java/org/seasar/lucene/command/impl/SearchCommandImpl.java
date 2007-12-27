package org.seasar.lucene.command.impl;

import java.sql.Connection;

import javax.sql.DataSource;

import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.extension.jdbc.util.DataSourceUtil;
import org.seasar.lucene.common.CommandType;
import org.seasar.lucene.meta.LuceneFunctionMetaData;
import org.seasar.lucene.meta.LuceneMetaData;

public class SearchCommandImpl extends AbstractCommandImpl {

	private DataSource dataSource;

	public Object execute(LuceneMetaData luceneMetaData, LuceneFunctionMetaData luceneFunctionMetaData, Object argument) {
		Connection connection = null;
		try {
			connection = DataSourceUtil.getConnection(dataSource);
			return search(luceneMetaData, luceneFunctionMetaData, argument);
		} finally {
			ConnectionUtil.close(connection);
		}
	}

	private Object search(LuceneMetaData luceneMetaData, LuceneFunctionMetaData luceneFunctionMetaData, Object argument) {
		return null;
	}

	public CommandType getTypeName() {
		return CommandType.SEARCH;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
