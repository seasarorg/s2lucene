package org.seasar.lucene.interceptor;

import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.S2MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.lucene.accessor.DirectoryAccessor;
import org.seasar.lucene.metadata.LuceneDtoMetaData;
import org.seasar.lucene.metadata.LuceneDtoMetaDataFactory;
import org.seasar.lucene.metadata.LuceneLuoMetaData;
import org.seasar.lucene.metadata.LuceneLuoMetaDataFactory;
import org.seasar.lucene.operation.DeleteDocumentOperation;
import org.seasar.lucene.operation.SearchDocumentOperation;
import org.seasar.lucene.operation.WriteDocumentOperation;

public class S2LuceneInterceptor extends AbstractInterceptor {

	private DirectoryAccessor directoryAccessor;

	private DeleteDocumentOperation deleteOperation;

	private WriteDocumentOperation writeOperation;

	private SearchDocumentOperation searchOperation;

	private String createMethodSuffix;

	private String updateMethodSuffix;

	private String deleteMethodSuffix;

	private String searchMethodSuffix;

	private void createMethod(List<LuceneDtoMetaData> metaDatas, Object targetData) {
		writeOperation.writeDocument(metaDatas, targetData, false);
	}

	private void updateMethod(List<LuceneDtoMetaData> metaDatas, Object targetData) {
		int targetId = searchOperation.check(metaDatas, targetData);
		deleteOperation.deleteDocument(targetId);
		writeOperation.writeDocument(metaDatas, targetData, false);
	}

	private void deleteMethod(List<LuceneDtoMetaData> metaDatas, Object targetData) {
		int targetId = searchOperation.check(metaDatas, targetData);
		deleteOperation.deleteDocument(targetId);
	}

	private Object searchMethod(List<LuceneDtoMetaData> metaDatas, Object targetData, LuceneLuoMetaData metaData) {
		return searchOperation.searchDocument(metaDatas, targetData, metaData);
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (!(invocation instanceof S2MethodInvocation)) {
			return null;
		}
		Object retValue = null;
		Object targetData = (Object) invocation.getArguments()[0];
		LuceneLuoMetaData metaData = LuceneLuoMetaDataFactory.createMetaData(invocation.getClass(), invocation.getMethod());
		List<LuceneDtoMetaData> metaDatas = LuceneDtoMetaDataFactory.createMetaData(metaData.getDtoClass());
		if (invocation.getMethod().getName().startsWith(createMethodSuffix)) {
			createMethod(metaDatas, targetData);
		}
		if (invocation.getMethod().getName().startsWith(updateMethodSuffix)) {
			updateMethod(metaDatas, targetData);
		}
		if (invocation.getMethod().getName().startsWith(deleteMethodSuffix)) {
			deleteMethod(metaDatas, targetData);
		}
		if (invocation.getMethod().getName().startsWith(searchMethodSuffix)) {
			retValue = searchMethod(metaDatas, targetData, metaData);
		}
		directoryAccessor.close();
		return retValue;
	}

	public DirectoryAccessor getDirectoryAccessor() {
		return directoryAccessor;
	}

	public void setDirectoryAccessor(DirectoryAccessor directoryAccessor) {
		this.directoryAccessor = directoryAccessor;
	}

	public DeleteDocumentOperation getDeleteOperation() {
		return deleteOperation;
	}

	public void setDeleteOperation(DeleteDocumentOperation deleteOperation) {
		this.deleteOperation = deleteOperation;
	}

	public WriteDocumentOperation getWriteOperation() {
		return writeOperation;
	}

	public void setWriteOperation(WriteDocumentOperation writeOperation) {
		this.writeOperation = writeOperation;
	}

	public SearchDocumentOperation getSearchOperation() {
		return searchOperation;
	}

	public void setSearchOperation(SearchDocumentOperation searchOperation) {
		this.searchOperation = searchOperation;
	}

	public String getCreateMethodSuffix() {
		return createMethodSuffix;
	}

	public void setCreateMethodSuffix(String createMethodSuffix) {
		this.createMethodSuffix = createMethodSuffix;
	}

	public String getUpdateMethodSuffix() {
		return updateMethodSuffix;
	}

	public void setUpdateMethodSuffix(String updateMethodSuffix) {
		this.updateMethodSuffix = updateMethodSuffix;
	}

	public String getDeleteMethodSuffix() {
		return deleteMethodSuffix;
	}

	public void setDeleteMethodSuffix(String deleteMethodSuffix) {
		this.deleteMethodSuffix = deleteMethodSuffix;
	}

	public String getSearchMethodSuffix() {
		return searchMethodSuffix;
	}

	public void setSearchMethodSuffix(String searchMethodSuffix) {
		this.searchMethodSuffix = searchMethodSuffix;
	}

}
