package learning;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ja.JapaneseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class LuceneLearningTest {

	IndexWriter writer = null;

	Directory directory = null;

	Analyzer analyzer = null;

	Document document = null;

	Field field = null;

	IndexSearcher searcher = null;

	Query query = null;

	QueryParser queryParser = null;

	String indexPath = "C:\\temp\\index";

	String configPath = System.getProperty("user.dir") + "\\sen\\conf\\sen.xml";

	/**
	 * テスト開始前に作成済みIndexを削除
	 */
	@Before
	public void inithializeIndex() {
		File indexDir = new File(indexPath);
		try {
			System.out.println("Index用ディレクトリがすでに存在するため削除します。");
			FileUtils.cleanDirectory(indexDir);
		} catch (IllegalArgumentException e) {
			System.out.println("Index用ディレクトリを作成します。");
			indexDir.mkdirs();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeIndex(boolean isAutoCommit, boolean isAbort, DocumentDto dto) {
		writer = createIndexWriter(directory, isAutoCommit, analyzer);
		createDocument(dto);
		addDocument(writer, document);
		if (isAutoCommit == true) {
			// flushした時点でIndexが書き込まれる(closeしない限り開いた前のIndexしか対象にならない)
			// ただしautoCommit=falseの場合のみ
			flush(writer);
		}
		if (isAutoCommit == false && isAbort == true) {
			// 書き込んだIndexを破棄する
			// ただしautoCommit=falseの場合のみ
			abort(writer);
		}
		if (isAutoCommit == false && isAbort == false) {
			// 書き込んだIndexを破棄する
			// ただしautoCommit=falseの場合のみ
			close(writer);
		}
	}

	private Hits searchIndex() {
		queryParser = new QueryParser("Field1", analyzer);
		query = parse(queryParser, "あおいそら");
		searcher = createIndexSearcher(directory);
		Hits hitsResult = search(searcher, query);
		return hitsResult;
	}

	private void dispHitsResult(Hits hitsResult) {
		Document docResult = doc(hitsResult, 0);
		String resultStr = docResult.get("Field1");
		System.out.println(resultStr);
	}

	@Ignore
	@Test
	public void basicRun() {
		try {
			directory = getDirectory(indexPath);
			analyzer = new JapaneseAnalyzer(configPath);

			DocumentDto dto = new DocumentDto();
			dto.setFieldName("Field1");
			dto.setValue("あおいそら、あおいうみ、とおきしま");
			dto.setStore(Store.YES);
			dto.setIndex(Index.TOKENIZED);
			writeIndex(false, true, dto);

			Hits hitsResult = searchIndex();
			dispHitsResult(hitsResult);
		} finally {
			close(searcher);
			close(writer);
			close(directory);
		}
	}

	private void createDocument(DocumentDto dto) {
		document = new Document();
		field = new Field(dto.getFieldName(), dto.getValue(), dto.getStore(), dto.getIndex());
		document.add(field);
	}

	/* =================================================================== */

	public class DocumentDto {
		private String fieldName;

		private String value;

		private Store store;

		private Index index;

		public String getFieldName() {
			return this.fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public Index getIndex() {
			return this.index;
		}

		public void setIndex(Index index) {
			this.index = index;
		}

		public Store getStore() {
			return this.store;
		}

		public void setStore(Store store) {
			this.store = store;
		}

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	/* =================================================================== */

	private void abort(IndexWriter writer) {
		try {
			writer.abort();
		} catch (IOException e) {
			throw new RuntimeException("abort IOException", e);
		}
	}

	private void flush(IndexWriter writer) {
		try {
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException("flush IOException", e);
		}
	}

	private void close(Directory directory) {
		try {
			if (directory != null) {
				directory.close();
			}
		} catch (IOException e) {
			throw new RuntimeException("close IOException", e);
		}
	}

	private void close(IndexWriter writer) {
		try {
			if (writer != null) {
				writer.close();
			}
		} catch (CorruptIndexException e) {
			throw new RuntimeException("close CorruptIndexException", e);
		} catch (IOException e) {
			throw new RuntimeException("close IOException", e);
		}
	}

	private void close(IndexSearcher searcher) {
		try {
			if (searcher != null) {
				searcher.close();
			}
		} catch (IOException e) {
			throw new RuntimeException("close IOException", e);
		}
	}

	private Document doc(Hits hitsResult, int index) {
		try {
			return hitsResult.doc(index);
		} catch (CorruptIndexException e) {
			throw new RuntimeException("doc CorruptIndexException", e);
		} catch (IOException e) {
			throw new RuntimeException("doc IOException", e);
		}
	}

	private Hits search(IndexSearcher searcher, Query query) {
		try {
			return searcher.search(query);
		} catch (IOException e) {
			throw new RuntimeException("search IOException", e);
		}
	}

	private Query parse(QueryParser queryParser, String searchWord) {
		try {
			return queryParser.parse(searchWord);
		} catch (ParseException e) {
			throw new RuntimeException("parse ParseException", e);
		}
	}

	private IndexSearcher createIndexSearcher(Directory directory) {
		try {
			return new IndexSearcher(directory);
		} catch (CorruptIndexException e) {
			throw new RuntimeException("createIndexSearcher CorruptIndexException", e);
		} catch (IOException e) {
			throw new RuntimeException("createIndexSearcher IOException", e);
		}
	}

	private void addDocument(IndexWriter writer, Document document) {
		try {
			writer.addDocument(document);
		} catch (CorruptIndexException e) {
			throw new RuntimeException("addDocument CorruptIndexException", e);
		} catch (IOException e) {
			throw new RuntimeException("addDocument IOException", e);
		}
	}

	private IndexWriter createIndexWriter(Directory directory, boolean autoCommit, Analyzer analyzer) {
		try {
			return new IndexWriter(directory, autoCommit, analyzer);
		} catch (IOException e) {
			throw new RuntimeException("createIndexWriter IOException", e);
		}
	}

	private Directory getDirectory(String path) {
		try {
			return FSDirectory.getDirectory(path);
		} catch (IOException e) {
			throw new RuntimeException("getDirectory IOException", e);
		}
	}

}
