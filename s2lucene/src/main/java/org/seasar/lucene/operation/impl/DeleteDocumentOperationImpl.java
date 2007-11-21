package org.seasar.lucene.operation.impl;

import org.apache.lucene.index.IndexReader;
import org.seasar.lucene.accessor.DirectoryAccessor;
import org.seasar.lucene.operation.DeleteDocumentOperation;
import org.seasar.lucene.util.IndexReaderUtil;


public class DeleteDocumentOperationImpl implements DeleteDocumentOperation {

	private DirectoryAccessor directoryAccessor;

	public void deleteDocument(int documentId) {
		IndexReader indexReader = IndexReaderUtil.open(directoryAccessor.getDirectory());
		IndexReaderUtil.deleteDocument(indexReader, documentId);
		IndexReaderUtil.close(indexReader);
	}

	public DirectoryAccessor getDirectoryAccessor() {
		return directoryAccessor;
	}

	public void setDirectoryAccessor(DirectoryAccessor directoryAccessor) {
		this.directoryAccessor = directoryAccessor;
	}

}
