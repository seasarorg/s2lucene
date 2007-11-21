package org.seasar.lucene.exception;

import org.apache.lucene.index.CorruptIndexException;

public class CorrupIndexRuntimeException extends BaseRuntimeException {

	public CorrupIndexRuntimeException(CorruptIndexException e) {
		super(e.getMessage(), e.getCause());
	}

}
