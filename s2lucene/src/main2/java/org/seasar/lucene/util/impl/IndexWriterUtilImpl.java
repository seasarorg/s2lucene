package org.seasar.lucene.util.impl;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.seasar.lucene.util.IndexWriterUtil;

public class IndexWriterUtilImpl implements IndexWriterUtil {

	public IndexWriter createIndexWriter(RAMDirectory ramDirectory, Analyzer analyzer) {
		try {
			return new IndexWriter(ramDirectory, analyzer);
		} catch (LockObtainFailedException e) {
			throw new RuntimeException(e);
		} catch (CorruptIndexException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void addDocument(IndexWriter indexWriter, Document doc) {
		try {
			indexWriter.addDocument(doc);
		} catch (CorruptIndexException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
