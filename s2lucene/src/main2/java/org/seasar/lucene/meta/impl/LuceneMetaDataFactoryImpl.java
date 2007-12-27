package org.seasar.lucene.meta.impl;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.lucene.meta.LuceneMetaData;
import org.seasar.lucene.meta.LuceneMetaDataFactory;

public class LuceneMetaDataFactoryImpl implements LuceneMetaDataFactory {

	private LuceneMetaData luceneMetaData;

	public LuceneMetaData createMetaData(MethodInvocation invocation) {
		luceneMetaData = new LuceneMetaDataImpl();
		setupMetaData(invocation);
		return luceneMetaData;
	}

	private void setupMetaData(MethodInvocation invocation) {
	}

}
