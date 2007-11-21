package org.seasar.lucene.dto;

import org.apache.lucene.document.Document;

public class HitsDto {

	private Document document;

	private int id;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
