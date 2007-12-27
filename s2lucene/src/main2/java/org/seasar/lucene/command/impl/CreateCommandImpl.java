package org.seasar.lucene.command.impl;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.RAMDirectory;
import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.extension.jdbc.util.DataSourceUtil;
import org.seasar.lucene.analyzer.AnalyzerFactory;
import org.seasar.lucene.common.CommandType;
import org.seasar.lucene.jdbc.LuceneConnection;
import org.seasar.lucene.meta.LuceneFunctionMetaData;
import org.seasar.lucene.meta.LuceneMetaData;
import org.seasar.lucene.util.IndexWriterUtil;

public class CreateCommandImpl extends AbstractCommandImpl {

	private DataSource dataSource;
	private IndexWriterUtil indexWriterUtil;
	private AnalyzerFactory analyzerFactory;

	public Object execute(LuceneMetaData luceneMetaData, LuceneFunctionMetaData luceneFunctionMetaData, Object argument) {
		Object result = null;
		Connection connection = null;
		try {
			connection = DataSourceUtil.getConnection(dataSource);
			init(connection);
			result = create(connection, luceneMetaData, luceneFunctionMetaData, argument);
			return result;
		} finally {
			ConnectionUtil.close(connection);
		}
	}

	private void init(Connection connection) {
		RAMDirectory ramDirectory = new RAMDirectory();
		Analyzer analyzer = analyzerFactory.createAnalyzer();
		IndexWriter ramIndexWriter = indexWriterUtil.createIndexWriter(ramDirectory, analyzer);
		((LuceneConnection) connection).setIndexWriter(ramIndexWriter);
	}

	private Object create(Connection connection, LuceneMetaData luceneMetaData, LuceneFunctionMetaData luceneFunctionMetaData, Object argument) {
		IndexWriter indexWriter = ((LuceneConnection) connection).getIndexWriter();
		//
		Document doc = new Document();
		Fieldable field = new Field("text", "Lucene入門", Store.YES, Index.TOKENIZED);
		doc.add(field);
		indexWriterUtil.addDocument(indexWriter, doc);
		//
		return null;
	}

	public CommandType getTypeName() {
		return CommandType.CREATE;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public IndexWriterUtil getIndexWriterUtil() {
		return indexWriterUtil;
	}

	public void setIndexWriterUtil(IndexWriterUtil indexWriterUtil) {
		this.indexWriterUtil = indexWriterUtil;
	}

}
