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

	@Binding(bindingType = BindingType.MUST)
	public DirectoryAccessor directoryAccessor;

	@Binding(bindingType = BindingType.MUST)
	public DeleteDocumentOperation deleteOperation;

	@Binding(bindingType = BindingType.MUST)
	public WriteDocumentOperation writeOperation;

	@Binding(bindingType = BindingType.MUST)
	public SearchDocumentOperation searchOperation;

	@Binding(bindingType = BindingType.MUST)
	public String createMethodSuffix;

	@Binding(bindingType = BindingType.MUST)
	public String updateMethodSuffix;

	@Binding(bindingType = BindingType.MUST)
	public String deleteMethodSuffix;

	@Binding(bindingType = BindingType.MUST)
	public String searchMethodSuffix;

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

}
