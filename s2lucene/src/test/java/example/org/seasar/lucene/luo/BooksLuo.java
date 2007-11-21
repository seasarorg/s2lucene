package example.org.seasar.lucene.luo;

import java.util.List;

import org.seasar.lucene.annotation.S2Lucene;
import org.seasar.lucene.annotation.Search;

import example.org.seasar.lucene.dto.BooksDto;

@S2Lucene(dto = BooksDto.class)
public interface BooksLuo {

	@Search(fieldName = "id", type = "")
	public List<BooksDto> searchById(String id);

	@Search(fieldName = "name", type = "")
	public List<BooksDto> searchByName(String searchWord);

	public void createIndex(BooksDto dto);

	public void updateIndex(BooksDto dto);

	public void deleteIndex(BooksDto dto);

}
