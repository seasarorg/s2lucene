package org.seasar.lucene;

import org.seasar.lucene.annotation.LuceneField;
import org.seasar.lucene.annotation.LuceneID;

public class DocumentDto {

	@LuceneID
	private long luceneID;

	@LuceneField
	private String text;

	public long getLuceneID() {
		return luceneID;
	}

	public void setLuceneID(long luceneID) {
		this.luceneID = luceneID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
