package org.seasar.lucene.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.util.MethodUtil;
import org.seasar.lucene.command.LuceneCommand;
import org.seasar.lucene.command.LuceneCommandFactory;
import org.seasar.lucene.meta.LuceneFunctionMetaData;
import org.seasar.lucene.meta.LuceneFunctionMetaDataFactory;
import org.seasar.lucene.meta.LuceneMetaData;
import org.seasar.lucene.meta.LuceneMetaDataFactory;

public class S2LuceneInterceptor extends AbstractInterceptor {

	private LuceneMetaDataFactory luceneMetaDataFactory;
	private LuceneFunctionMetaDataFactory luceneFunctionMetaDataFactory;
	private LuceneCommandFactory luceneCommandFactory;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		if (!MethodUtil.isAbstract(method)) {
			return invocation.proceed();
		}
		// Dtoより取得するメタデータ作成
		LuceneMetaData luceneMetaData = luceneMetaDataFactory.createMetaData(invocation);
		// Luoより取得するメタデータ作成
		LuceneFunctionMetaData luceneFunctionMetaData = luceneFunctionMetaDataFactory.crateMetaData(invocation);
		// 実行するコマンド作成
		LuceneCommand luceneCommand = luceneCommandFactory.getCommand(invocation);
		// コマンド実行
		Object argument = invocation.getArguments()[0];
		Object result = luceneCommand.execute(luceneMetaData, luceneFunctionMetaData, argument);
		return result;
	}

	public LuceneMetaDataFactory getLuceneMetaDataFactory() {
		return luceneMetaDataFactory;
	}

	public void setLuceneMetaDataFactory(LuceneMetaDataFactory luceneMetaDataFactory) {
		this.luceneMetaDataFactory = luceneMetaDataFactory;
	}

	public LuceneFunctionMetaDataFactory getLuceneFunctionMetaDataFactory() {
		return luceneFunctionMetaDataFactory;
	}

	public void setLuceneFunctionMetaDataFactory(LuceneFunctionMetaDataFactory luceneFunctionMetaDataFactory) {
		this.luceneFunctionMetaDataFactory = luceneFunctionMetaDataFactory;
	}

	public LuceneCommandFactory getLuceneCommandFactory() {
		return luceneCommandFactory;
	}

	public void setLuceneCommandFactory(LuceneCommandFactory luceneCommandFactory) {
		this.luceneCommandFactory = luceneCommandFactory;
	}

}
