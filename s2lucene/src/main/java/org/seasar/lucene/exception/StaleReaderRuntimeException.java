package org.seasar.lucene.exception;

import org.apache.lucene.index.StaleReaderException;

public class StaleReaderRuntimeException extends BaseRuntimeException {

	public StaleReaderRuntimeException(StaleReaderException e) {
		super(e.getMessage(), e.getCause());
	}

}
