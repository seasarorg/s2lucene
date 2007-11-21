package org.seasar.lucene.exception;

public class IllegalAccessRuntimeException extends BaseRuntimeException {

	public IllegalAccessRuntimeException(IllegalAccessException e) {
		super(e.getMessage(), e.getCause());
	}
}
