package org.seasar.lucene.meta;

import org.aopalliance.intercept.MethodInvocation;

public interface LuceneFunctionMetaDataFactory {

	LuceneFunctionMetaData crateMetaData(MethodInvocation invocation);

}
