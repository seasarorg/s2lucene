package org.seasar.lucene.util;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.seasar.lucene.exception.CorrupIndexRuntimeException;
import org.seasar.lucene.exception.IORuntimeException;
import org.seasar.lucene.exception.LockObtainFailedRuntimeException;


public class IndexWriterUtil {

	public static IndexWriter create(Directory directory, Analyzer analyzer, boolean create) {
		try {
			return new IndexWriter(directory, analyzer);
		} catch (CorruptIndexException e) {
			throw new CorrupIndexRuntimeException(e);
		} catch (LockObtainFailedException e) {
			throw new LockObtainFailedRuntimeException(e);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void addDocument(IndexWriter indexWriter, Document document) {
		try {
			indexWriter.addDocument(document);
		} catch (CorruptIndexException e) {
			throw new CorrupIndexRuntimeException(e);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void close(IndexWriter indexWriter) {
		if (indexWriter != null) {
			try {
				indexWriter.close();
			} catch (CorruptIndexException e) {
				throw new CorrupIndexRuntimeException(e);
			} catch (IOException e) {
				throw new IORuntimeException(e);
			}
		}
	}

}
