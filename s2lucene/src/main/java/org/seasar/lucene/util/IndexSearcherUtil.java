package org.seasar.lucene.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.seasar.lucene.dto.HitsDto;
import org.seasar.lucene.exception.CorrupIndexRuntimeException;
import org.seasar.lucene.exception.IORuntimeException;
import org.seasar.lucene.exception.LockObtainFailedRuntimeException;


public class IndexSearcherUtil {

	public static IndexSearcher create(Directory directory) {
		try {
			return new IndexSearcher(directory);
		} catch (CorruptIndexException e) {
			throw new CorrupIndexRuntimeException(e);
		} catch (LockObtainFailedException e) {
			throw new LockObtainFailedRuntimeException(e);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static List<HitsDto> search(IndexSearcher indexSearch, Query query) {
		List<HitsDto> resultList = new ArrayList<HitsDto>();
		try {
			Hits hits = indexSearch.search(query);
			for (int i = 0; i < hits.length(); i++) {
				HitsDto dto = new HitsDto();
				dto.setDocument(hits.doc(i));
				dto.setId(hits.id(i));
				resultList.add(dto);
			}
			return resultList;
		} catch (CorruptIndexException e) {
			throw new CorrupIndexRuntimeException(e);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void close(IndexSearcher indexSearch) {
		if (indexSearch != null) {
			try {
				indexSearch.close();
			} catch (CorruptIndexException e) {
				throw new CorrupIndexRuntimeException(e);
			} catch (IOException e) {
				throw new IORuntimeException(e);
			}
		}
	}

}
