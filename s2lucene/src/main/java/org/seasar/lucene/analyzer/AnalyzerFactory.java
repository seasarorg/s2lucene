package org.seasar.lucene.analyzer;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.seasar.lucene.metadata.LuceneDtoMetaData;


public interface AnalyzerFactory {

	public Analyzer createAnalyzer(List<LuceneDtoMetaData> metaDatas);

}
