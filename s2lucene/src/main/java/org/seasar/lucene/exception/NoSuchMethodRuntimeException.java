package org.seasar.lucene.exception;

public class NoSuchMethodRuntimeException extends BaseRuntimeException {

	public NoSuchMethodRuntimeException(NoSuchMethodException e) {
		super(e.getMessage(), e.getCause());
	}
}
