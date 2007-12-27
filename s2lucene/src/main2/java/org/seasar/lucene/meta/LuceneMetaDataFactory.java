package org.seasar.lucene.meta;

import org.aopalliance.intercept.MethodInvocation;

public interface LuceneMetaDataFactory {

	LuceneMetaData createMetaData(MethodInvocation invocation);
}
