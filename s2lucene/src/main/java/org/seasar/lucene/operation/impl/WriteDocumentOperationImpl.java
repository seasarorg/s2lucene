package org.seasar.lucene.operation.impl;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.seasar.lucene.accessor.DirectoryAccessor;
import org.seasar.lucene.analyzer.AnalyzerFactory;
import org.seasar.lucene.metadata.LuceneDtoMetaData;
import org.seasar.lucene.operation.WriteDocumentOperation;
import org.seasar.lucene.util.IndexWriterUtil;
import org.seasar.lucene.util.ValueUtil;


public class WriteDocumentOperationImpl implements WriteDocumentOperation {

	private DirectoryAccessor directoryAccessor;
	private AnalyzerFactory analyzerFactory;

	public void writeDocument(List<LuceneDtoMetaData> metaDatas, Object targetData, boolean create) {
		Document document = createDocument(metaDatas, targetData);
		Analyzer analyzer = analyzerFactory.createAnalyzer(metaDatas);
		IndexWriter indexWriter = IndexWriterUtil.create(directoryAccessor.getDirectory(), analyzer, create);
		IndexWriterUtil.addDocument(indexWriter, document);
		IndexWriterUtil.close(indexWriter);
	}

	private Document createDocument(List<LuceneDtoMetaData> metaDatas, Object target) {
		Document document = new Document();
		for (LuceneDtoMetaData metaData : metaDatas) {
			String retValue = ValueUtil.getValue(metaData, target);
			Field field = createField(metaData, retValue);
			document.add(field);
		}
		document.add(createDocumentType(metaDatas.get(0).getTypeName()));
		return document;
	}

	private Field createDocumentType(String typeName) {
		Field documentType = null;
		documentType = new Field("LuceneDocType", typeName, Store.YES, Index.UN_TOKENIZED);
		return documentType;
	}

	private Field createField(LuceneDtoMetaData metaData, String retValue) {
		Field field = null;
		// TODO とりあえず
		if (retValue == null) {
			retValue = "";
		}
		if (metaData.isStored() && metaData.isToknize()) {
			field = new Field(metaData.getFieldName(), retValue, Store.YES, Index.TOKENIZED);
		}
		if (!metaData.isStored() && metaData.isToknize()) {
			field = new Field(metaData.getFieldName(), retValue, Store.NO, Index.TOKENIZED);
		}
		if (metaData.isStored() && !metaData.isToknize()) {
			field = new Field(metaData.getFieldName(), retValue, Store.YES, Index.UN_TOKENIZED);
		}
		if (!metaData.isStored() && !metaData.isToknize()) {
			field = new Field(metaData.getFieldName(), retValue, Store.NO, Index.UN_TOKENIZED);
		}
		return field;
	}

	public DirectoryAccessor getDirectoryAccessor() {
		return directoryAccessor;
	}

	public void setDirectoryAccessor(DirectoryAccessor directoryAccessor) {
		this.directoryAccessor = directoryAccessor;
	}

	public AnalyzerFactory getAnalyzerFactory() {
		return analyzerFactory;
	}

	public void setAnalyzerFactory(AnalyzerFactory analyzerFactory) {
		this.analyzerFactory = analyzerFactory;
	}

}
