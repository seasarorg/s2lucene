package org.seasar.lucene.analyzer.impl;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.ja.JapaneseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.seasar.lucene.analyzer.AnalyzerFactory;

public class AnalyzerFactoryImpl implements AnalyzerFactory {

	private JapaneseAnalyzer japaneseAnalyzer;

	private CJKAnalyzer cjkAnalyzer;

	private SimpleAnalyzer simpleAnalyzer;

	private StandardAnalyzer standardAnalyzer;

	private DummyAnalyzerImpl dummyAnalyzer;

	private PerFieldAnalyzerWrapper perFieldAnalyzerWrapper;

	public AnalyzerFactoryImpl() {
		perFieldAnalyzerWrapper = new PerFieldAnalyzerWrapper(dummyAnalyzer);
	}

	public Analyzer createAnalyzer() {
		return perFieldAnalyzerWrapper;
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

	public SimpleAnalyzer getSimpleAnalyzer() {
		return simpleAnalyzer;
	}

	public void setSimpleAnalyzer(SimpleAnalyzer simpleAnalyzer) {
		this.simpleAnalyzer = simpleAnalyzer;
	}

	public DummyAnalyzerImpl getDummyAnalyzer() {
		return dummyAnalyzer;
	}

	public void setDummyAnalyzer(DummyAnalyzerImpl dummyAnalyzer) {
		this.dummyAnalyzer = dummyAnalyzer;
	}

	public StandardAnalyzer getStandardAnalyzer() {
		return standardAnalyzer;
	}

	public void setStandardAnalyzer(StandardAnalyzer standardAnalyzer) {
		this.standardAnalyzer = standardAnalyzer;
	}

}
