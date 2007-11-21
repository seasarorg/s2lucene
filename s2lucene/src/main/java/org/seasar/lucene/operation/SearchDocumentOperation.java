package org.seasar.lucene.operation;

import java.util.List;

import org.seasar.lucene.metadata.LuceneDtoMetaData;
import org.seasar.lucene.metadata.LuceneLuoMetaData;


public interface SearchDocumentOperation {

	public List searchDocument(List<LuceneDtoMetaData> metaDatas, Object targetData, LuceneLuoMetaData metaData);

	public int check(List<LuceneDtoMetaData> metaDatas, Object targetData);

}
