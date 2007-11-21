package example2.org.seasar.lucene.luo;

import java.util.List;

import org.seasar.lucene.annotation.S2Lucene;
import org.seasar.lucene.annotation.Search;

import example2.org.seasar.lucene.dto.DocumentsDto;

@S2Lucene(dto = DocumentsDto.class)
public interface DocumentsLuo {

	@Search(fieldName = "id", type = "")
	public List<DocumentsDto> searchById(String id);

	@Search(fieldName = "name", type = "")
	public List<DocumentsDto> searchByName(String searchWord);

	public void createIndex(DocumentsDto dto);

	public void updateIndex(DocumentsDto dto);

	public void deleteIndex(DocumentsDto dto);

}
