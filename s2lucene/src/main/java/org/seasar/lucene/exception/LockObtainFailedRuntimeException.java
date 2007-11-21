package org.seasar.lucene.exception;

import org.apache.lucene.store.LockObtainFailedException;

public class LockObtainFailedRuntimeException extends BaseRuntimeException {

	public LockObtainFailedRuntimeException(LockObtainFailedException e) {
		super(e.getMessage(), e.getCause());
	}

}
