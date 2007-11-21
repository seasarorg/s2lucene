package org.seasar.lucene.exception;

import org.apache.lucene.queryParser.ParseException;

public class ParseRuntimeException extends BaseRuntimeException {

	public ParseRuntimeException(ParseException e) {
		super(e.getMessage(), e.getCause());
	}

}
