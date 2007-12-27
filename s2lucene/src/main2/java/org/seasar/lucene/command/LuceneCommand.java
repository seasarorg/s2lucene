package org.seasar.lucene.command;

import org.seasar.lucene.common.CommandType;
import org.seasar.lucene.meta.LuceneFunctionMetaData;
import org.seasar.lucene.meta.LuceneMetaData;

public interface LuceneCommand {

	public CommandType getTypeName();

	public Object execute(LuceneMetaData luceneMetaData, LuceneFunctionMetaData luceneFunctionMetaData, Object argument);

}
