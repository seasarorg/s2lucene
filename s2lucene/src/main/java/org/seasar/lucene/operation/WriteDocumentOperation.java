package org.seasar.lucene.operation;

import java.util.List;

import org.seasar.lucene.metadata.LuceneDtoMetaData;


public interface WriteDocumentOperation {

	public void writeDocument(List<LuceneDtoMetaData> metaDatas, Object targetData, boolean create);

}
