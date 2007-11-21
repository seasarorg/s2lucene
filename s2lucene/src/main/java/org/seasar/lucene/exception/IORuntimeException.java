package org.seasar.lucene.exception;

import java.io.IOException;

public class IORuntimeException extends BaseRuntimeException {

	public IORuntimeException(IOException e) {
		super(e.getMessage(), e.getCause());
	}
}
