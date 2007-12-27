package org.seasar.lucene.meta.impl;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.lucene.meta.LuceneFunctionMetaData;
import org.seasar.lucene.meta.LuceneFunctionMetaDataFactory;

public class LuceneFunctionMetaDataFactoryImpl implements LuceneFunctionMetaDataFactory {

	private LuceneFunctionMetaData luceneFunctionMetaData;

	public LuceneFunctionMetaData crateMetaData(MethodInvocation invocation) {
		luceneFunctionMetaData = new LuceneFunctionMetaDataImpl();
		setupMetaData(invocation);
		return luceneFunctionMetaData;
	}

	private void setupMetaData(MethodInvocation invocation) {
	}

}
