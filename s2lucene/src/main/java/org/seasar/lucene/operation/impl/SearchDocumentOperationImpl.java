package org.seasar.lucene.operation.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.seasar.lucene.accessor.DirectoryAccessor;
import org.seasar.lucene.analyzer.AnalyzerFactory;
import org.seasar.lucene.dto.HitsDto;
import org.seasar.lucene.exception.TargetNotFoundRuntimeException;
import org.seasar.lucene.metadata.LuceneDtoMetaData;
import org.seasar.lucene.metadata.LuceneLuoMetaData;
import org.seasar.lucene.operation.SearchDocumentOperation;
import org.seasar.lucene.util.IDUtil;
import org.seasar.lucene.util.IndexSearcherUtil;
import org.seasar.lucene.util.QueryParserUtil;
import org.seasar.lucene.util.ValueUtil;


public class SearchDocumentOperationImpl implements SearchDocumentOperation {

	private DirectoryAccessor directoryAccessor;
	private AnalyzerFactory analyzerFactory;

	public List searchDocument(List<LuceneDtoMetaData> metaDatas, Object targetData, LuceneLuoMetaData metaData) {
		String saerchWord = (String) targetData;
		QueryParser parser = createQueryParser(metaData, metaDatas);

		Query documentTypeQuery = createDocumentTypeQuery(metaDatas);
		Query searchQuery = QueryParserUtil.createSearchQuery(parser, saerchWord);

		BooleanQuery booleanQuery = new BooleanQuery();
		booleanQuery.add(documentTypeQuery, BooleanClause.Occur.MUST);
		booleanQuery.add(searchQuery, BooleanClause.Occur.MUST);

		List<HitsDto> resultList = executeQuery(booleanQuery, metaDatas);
		Class dtoClass = metaData.getDtoClass();
		List<Object> convertedResults = convertDocumentToObject(resultList, metaDatas, dtoClass);
		return convertedResults;
	}

	public int check(List<LuceneDtoMetaData> metaDatas, Object targetData) {
		Query idQuery = createIDQuery(metaDatas, targetData);
		Query documentTypeQuery = createDocumentTypeQuery(metaDatas);

		BooleanQuery booleanQuery = new BooleanQuery();
		booleanQuery.add(idQuery, BooleanClause.Occur.MUST);
		booleanQuery.add(documentTypeQuery, BooleanClause.Occur.MUST);

		List<HitsDto> resultList = executeQuery(booleanQuery, metaDatas);
		if (resultList.size() != 1) {
			throw new TargetNotFoundRuntimeException();
		}
		int targetId = resultList.get(0).getId();
		return targetId;
	}

	private QueryParser createQueryParser(LuceneLuoMetaData metaData, List<LuceneDtoMetaData> metaDatas) {
		Analyzer analyzer = analyzerFactory.createAnalyzer(metaDatas);
		QueryParser parser = new QueryParser(metaData.getFieldName(), analyzer);
		if (metaData.getType().equals("AND")) {
			parser.setDefaultOperator(QueryParser.Operator.AND);
		}
		if (metaData.getType().equals("OR")) {
			parser.setDefaultOperator(QueryParser.Operator.OR);
		}
		return parser;
	}

	private Query createIDQuery(List<LuceneDtoMetaData> metaDatas, Object targetData) {
		String idFieldName = IDUtil.getIdName(metaDatas, targetData);
		String idValue = IDUtil.getIdValue(metaDatas, targetData);
		Term term = new Term(idFieldName, idValue);
		Query query = new TermQuery(term);
		return query;
	}

	private Query createDocumentTypeQuery(List<LuceneDtoMetaData> metaDatas) {
		String documentTypeFieldName = "LuceneDocType";
		String documentTypeFieldValue = metaDatas.get(0).getTypeName();
		Term term = new Term(documentTypeFieldName, documentTypeFieldValue);
		Query query = new TermQuery(term);
		return query;
	}

	private List<HitsDto> executeQuery(Query query, List<LuceneDtoMetaData> metaDatas) {
		IndexSearcher indexSearcher = IndexSearcherUtil.create(directoryAccessor.getDirectory());
		List<HitsDto> resultList = IndexSearcherUtil.search(indexSearcher, query);
		IndexSearcherUtil.close(indexSearcher);
		return resultList;
	}

	private List<Object> convertDocumentToObject(List<HitsDto> resultList, List<LuceneDtoMetaData> metaDatas, Class dtoClass) {
		List<Object> datas = new ArrayList<Object>();
		for (int i = 0; i < resultList.size(); i++) {
			HitsDto dto = resultList.get(i);
			Document document = dto.getDocument();
			Object target = ValueUtil.setupObject(document, metaDatas, dtoClass);
			datas.add(target);
		}
		return datas;
	}

	public DirectoryAccessor getDirectoryAccessor() {
		return directoryAccessor;
	}

	public void setDirectoryAccessor(DirectoryAccessor directoryAccessor) {
		this.directoryAccessor = directoryAccessor;
	}

	public AnalyzerFactory getAnalyzerFactory() {
		return analyzerFactory;
	}

	public void setAnalyzerFactory(AnalyzerFactory analyzerFactory) {
		this.analyzerFactory = analyzerFactory;
	}

}
