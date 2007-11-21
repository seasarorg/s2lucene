package org.seasar.lucene.exception;

import java.lang.reflect.InvocationTargetException;

public class InvocationTargetRuntimeException extends BaseRuntimeException {

	public InvocationTargetRuntimeException(InvocationTargetException e) {
		super(e.getMessage(), e.getCause());
	}
}
