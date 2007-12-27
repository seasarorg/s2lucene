package org.seasar.lucene.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.RAMDirectory;

public interface IndexWriterUtil {

	IndexWriter createIndexWriter(RAMDirectory ramDirectory, Analyzer analyzer);

	void addDocument(IndexWriter indexWriter, Document doc);

}
