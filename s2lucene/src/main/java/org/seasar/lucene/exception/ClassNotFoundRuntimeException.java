package org.seasar.lucene.exception;

public class ClassNotFoundRuntimeException extends BaseRuntimeException {

	public ClassNotFoundRuntimeException(ClassNotFoundException e) {
		super(e.getMessage(), e.getCause());
	}

}
