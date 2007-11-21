package org.seasar.lucene.util;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.seasar.lucene.exception.ParseRuntimeException;


public class QueryParserUtil {

	public static Query createSearchQuery(QueryParser parser, String saerchWord) {
		try {
			return parser.parse(saerchWord);
		} catch (ParseException e) {
			throw new ParseRuntimeException(e);
		}
	}

}
