package org.seasar.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;

public interface AnalyzerFactory {

	public Analyzer createAnalyzer();

}
