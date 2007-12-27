package org.seasar.lucene.analyzer.impl;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

public class DummyAnalyzerImpl extends Analyzer {

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		return null;
	}

}
