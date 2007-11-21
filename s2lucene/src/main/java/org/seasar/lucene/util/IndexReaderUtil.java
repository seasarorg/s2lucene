package org.seasar.lucene.util;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StaleReaderException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.seasar.lucene.exception.CorrupIndexRuntimeException;
import org.seasar.lucene.exception.IORuntimeException;
import org.seasar.lucene.exception.LockObtainFailedRuntimeException;
import org.seasar.lucene.exception.StaleReaderRuntimeException;


public class IndexReaderUtil {

	public static IndexReader open(Directory directory) {
		try {
			return IndexReader.open(directory);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void close(IndexReader indexReader) {
		if (indexReader != null) {
			try {
				indexReader.close();
			} catch (IOException e) {
				throw new IORuntimeException(e);
			}
		}
	}

	public static void deleteDocument(IndexReader indexReader, int deleteId) {
		try {
			indexReader.deleteDocument(deleteId);
		} catch (StaleReaderException e) {
			throw new StaleReaderRuntimeException(e);
		} catch (CorruptIndexException e) {
			throw new CorrupIndexRuntimeException(e);
		} catch (LockObtainFailedException e) {
			throw new LockObtainFailedRuntimeException(e);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}
}
