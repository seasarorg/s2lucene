package org.seasar.lucene.analyzer.impl;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.ja.JapaneseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.seasar.lucene.analyzer.AnalyzerFactory;
import org.seasar.lucene.metadata.LuceneDtoMetaData;

public class AnalyzerFactoryImpl implements AnalyzerFactory {

	private JapaneseAnalyzer japaneseAnalyzer;

	private CJKAnalyzer cjkAnalyzer;

	private StandardAnalyzer standardAnalyzer;

	public Analyzer createAnalyzer(List<LuceneDtoMetaData> metaDatas) {
		PerFieldAnalyzerWrapper analyzer = new PerFieldAnalyzerWrapper(japaneseAnalyzer);
		for (LuceneDtoMetaData metaData : metaDatas) {
			String type = metaData.getAnalyzerType();
			if (isCJKType(type)) {
				analyzer.addAnalyzer(metaData.getFieldName(), cjkAnalyzer);
			}
			if (isStandardType(type)) {
				analyzer.addAnalyzer(metaData.getFieldName(), standardAnalyzer);
			}
		}
		return analyzer;
	}

	private boolean isStandardType(String type) {
		return type.equals("Standard");
	}

	private boolean isCJKType(String type) {
		return type.equals("CJK");
	}

	public JapaneseAnalyzer getJapaneseAnalyzer() {
		return japaneseAnalyzer;
	}

	public void setJapaneseAnalyzer(JapaneseAnalyzer japaneseAnalyzer) {
		this.japaneseAnalyzer = japaneseAnalyzer;
	}

	public CJKAnalyzer getCjkAnalyzer() {
		return cjkAnalyzer;
	}

	public void setCjkAnalyzer(CJKAnalyzer cjkAnalyzer) {
		this.cjkAnalyzer = cjkAnalyzer;
	}

	public StandardAnalyzer getStandardAnalyzer() {
		return standardAnalyzer;
	}

	public void setStandardAnalyzer(StandardAnalyzer standardAnalyzer) {
		this.standardAnalyzer = standardAnalyzer;
	}

}
