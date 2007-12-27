package org.seasar.lucene.command;

import org.aopalliance.intercept.MethodInvocation;

public interface LuceneCommandFactory {

	LuceneCommand getCommand(MethodInvocation invocation);

}
