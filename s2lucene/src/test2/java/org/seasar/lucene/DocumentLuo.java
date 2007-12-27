package org.seasar.lucene;

import java.util.List;

import org.seasar.lucene.annotation.S2Lucene;

@S2Lucene(dtoClass = DocumentDto.class)
public interface DocumentLuo {

	List<DocumentDto> search(String searchWord);

	void create(DocumentDto dto);

	void update(DocumentDto dto);

	void delete(DocumentDto dto);

}
