package org.seasar.lucene.exception;

public class InstantiationRuntimeException extends BaseRuntimeException {

	public InstantiationRuntimeException(InstantiationException e) {
		super(e.getMessage(), e.getCause());
	}

}
